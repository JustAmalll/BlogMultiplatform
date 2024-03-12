package dev.amal.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import dev.amal.blogmultiplatform.components.AdminPageLayout
import dev.amal.blogmultiplatform.components.Button
import dev.amal.blogmultiplatform.components.LabeledSwitch
import dev.amal.blogmultiplatform.components.PostsView
import dev.amal.blogmultiplatform.components.SearchBar
import dev.amal.blogmultiplatform.models.Constants.POSTS_PER_PAGE
import dev.amal.blogmultiplatform.models.JsTheme
import dev.amal.blogmultiplatform.models.PostWithoutDetails
import dev.amal.blogmultiplatform.util.Constants.SIDE_PANEL_WIDTH
import dev.amal.blogmultiplatform.util.fetchMyPosts
import dev.amal.blogmultiplatform.util.isUserLoggedIn
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Page("/admin/myposts")
@Composable
fun MyPostsPage() {
    isUserLoggedIn {
        MyPostsScreen()
    }
}

@Composable
fun MyPostsScreen() {
    val context = rememberPageContext()
    val breakpoint = rememberBreakpoint()
    val scope = rememberCoroutineScope()

    val myPosts = remember { mutableStateListOf<PostWithoutDetails>() }
    val selectedPosts = remember { mutableStateListOf<String>() }

    var postsToSkip by remember { mutableStateOf(0) }
    var showMoreVisibility by remember { mutableStateOf(false) }

    var selectableMode by remember { mutableStateOf(false) }

    val fetchMyPosts: suspend () -> Unit = remember {
        {
            val result = fetchMyPosts(skip = postsToSkip)
            myPosts.addAll(result)
            postsToSkip += POSTS_PER_PAGE
            showMoreVisibility = result.size >= POSTS_PER_PAGE
        }
    }

    LaunchedEffect(Unit) {
        fetchMyPosts()
    }

    AdminPageLayout {
        Column(
            modifier = Modifier
                .margin(topBottom = 50.px)
                .fillMaxSize()
                .padding(left = if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(if (breakpoint > Breakpoint.MD) 30.percent else 50.percent)
                    .margin(bottom = 24.px),
                contentAlignment = Alignment.Center
            ) {
                SearchBar(
                    breakpoint = breakpoint,
                    modifier = Modifier
                        .transition(
                            CSSTransition(
                                property = TransitionProperty.All,
                                duration = 200.ms
                            )
                        ),
                    onEnterClick = {},
                    onSearchIconClick = {}
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(if (breakpoint > Breakpoint.MD) 80.percent else 90.percent)
                    .margin(bottom = 24.px),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                LabeledSwitch(
                    modifier = Modifier.margin(right = 8.px),
                    checked = selectableMode,
                    text = if (!selectableMode) {
                        "Select"
                    } else if (selectedPosts.size == 1 || selectedPosts.size == 0) {
                        "${selectedPosts.size} Post Selected"
                    } else {
                        "${selectedPosts.size} Posts Selected"
                    },
                    onCheckedChange = {
                        selectableMode = it
                        selectedPosts.clear()
                    }
                )
                Button(
                    modifier = Modifier.margin(right = 20.px),
                    text = "Delete",
                    onClick = {},
                    bgColor = JsTheme.Red.rgb
                )
            }
            PostsView(
                breakpoint = breakpoint,
                posts = myPosts,
                selectableMode = selectableMode,
                onSelect = { selectedPosts.add(it) },
                onDeselect = { selectedPosts.remove(it) },
                showMoreVisibility = showMoreVisibility,
                onShowMore = { scope.launch { fetchMyPosts() } },
                onClick = {}
            )
        }
    }
}