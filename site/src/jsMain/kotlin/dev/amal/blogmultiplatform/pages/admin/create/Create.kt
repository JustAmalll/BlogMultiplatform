package dev.amal.blogmultiplatform.pages.admin.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.SwitchSize
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import dev.amal.blogmultiplatform.components.AdminPageLayout
import dev.amal.blogmultiplatform.components.Button
import dev.amal.blogmultiplatform.components.ControlPopup
import dev.amal.blogmultiplatform.components.Input
import dev.amal.blogmultiplatform.components.LabeledSwitch
import dev.amal.blogmultiplatform.components.MessagePopup
import dev.amal.blogmultiplatform.models.ControlStyle
import dev.amal.blogmultiplatform.models.EditorControl
import dev.amal.blogmultiplatform.models.Post
import dev.amal.blogmultiplatform.navigation.Screen
import dev.amal.blogmultiplatform.pages.admin.create.components.CategoryDropdown
import dev.amal.blogmultiplatform.pages.admin.create.components.Editor
import dev.amal.blogmultiplatform.pages.admin.create.components.EditorControls
import dev.amal.blogmultiplatform.pages.admin.create.components.ThumbnailUploader
import dev.amal.blogmultiplatform.util.Constants.SIDE_PANEL_WIDTH
import dev.amal.blogmultiplatform.util.Id
import dev.amal.blogmultiplatform.util.addPost
import dev.amal.blogmultiplatform.util.applyStyle
import dev.amal.blogmultiplatform.util.getSelectedText
import dev.amal.blogmultiplatform.util.getTextAreaValueById
import dev.amal.blogmultiplatform.util.isUserLoggedIn
import kotlinx.browser.localStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.px
import org.w3c.dom.get
import kotlin.js.Date

@Page("/admin/create")
@Composable
fun CreatePage() {
    isUserLoggedIn {
        CreateScreen()
    }
}

@Composable
fun CreateScreen() {
    val context = rememberPageContext()
    val scope = rememberCoroutineScope()
    val breakpoint = rememberBreakpoint()
    var uiState by remember { mutableStateOf(CreatePageUiState()) }

    AdminPageLayout {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .margin(topBottom = 50.px)
                .padding(left = if (breakpoint > Breakpoint.MD) SIDE_PANEL_WIDTH.px else 0.px),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .maxWidth(700.px),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleGrid(numColumns = numColumns(base = 1, sm = 3)) {
                    LabeledSwitch(
                        modifier = Modifier.margin(
                            right = 24.px,
                            bottom = if (breakpoint < Breakpoint.SM) 12.px else 0.px
                        ),
                        checked = uiState.popular,
                        onCheckedChange = { uiState = uiState.copy(popular = it) },
                        text = "Popular"
                    )
                    LabeledSwitch(
                        modifier = Modifier.margin(
                            right = 24.px,
                            bottom = if (breakpoint < Breakpoint.SM) 12.px else 0.px
                        ),
                        checked = uiState.main,
                        onCheckedChange = { uiState = uiState.copy(main = it) },
                        text = "Main"
                    )
                    LabeledSwitch(
                        modifier = Modifier.margin(
                            bottom = if (breakpoint < Breakpoint.SM) 12.px else 0.px
                        ),
                        checked = uiState.sponsored,
                        onCheckedChange = { uiState = uiState.copy(sponsored = it) },
                        text = "Sponsored"
                    )
                }
                Input(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(topBottom = 12.px),
                    value = uiState.title,
                    onValueChanged = { uiState = uiState.copy(title = it) },
                    placeholder = "Title"
                )
                Input(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.subtitle,
                    onValueChanged = { uiState = uiState.copy(subtitle = it) },
                    placeholder = "Subtitle"
                )
                CategoryDropdown(
                    selectedCategory = uiState.category,
                    onCategorySelect = { uiState = uiState.copy(category = it) }
                )
                LabeledSwitch(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(topBottom = 12.px),
                    checked = !uiState.thumbnailInputDisabled,
                    onCheckedChange = {
                        uiState = uiState.copy(
                            thumbnailInputDisabled = !it,
                            thumbnail = "",
                            thumbnailInput = ""
                        )
                    },
                    text = "Paste an Image URL instead",
                    size = SwitchSize.MD
                )
                ThumbnailUploader(
                    thumbnailInput = uiState.thumbnailInput,
                    thumbnailInputDisabled = uiState.thumbnailInputDisabled,
                    onThumbnailSelect = { filename, file ->
                        uiState = uiState.copy(thumbnail = file, thumbnailInput = filename)
                    },
                    onThumbnailInputChanged = { uiState = uiState.copy(thumbnailInput = it) }
                )
                EditorControls(
                    breakpoint = breakpoint,
                    editorVisibility = uiState.editorVisibility,
                    onLinkClick = { uiState = uiState.copy(linkPopup = true) },
                    onImageClick = { uiState = uiState.copy(imagePopup = true) },
                    onEditorVisibilityChange = {
                        uiState = uiState.copy(editorVisibility = !uiState.editorVisibility)
                    }
                )
                Editor(editorVisibility = uiState.editorVisibility)

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(top = 24.px),
                    text = uiState.buttonText,
                    onClick = {
                        uiState = uiState.copy(content = getTextAreaValueById(id = Id.EDITOR))

                        scope.launch {
                            if (
                                uiState.title.isNotEmpty() &&
                                uiState.subtitle.isNotEmpty() &&
                                uiState.thumbnail.isNotEmpty() &&
                                uiState.content.isNotEmpty()
                            ) {
                                val result = addPost(
                                    Post(
                                        author = localStorage["username"].toString(),
                                        title = uiState.title,
                                        subtitle = uiState.subtitle,
                                        date = Date.now(),
                                        thumbnail = uiState.thumbnail,
                                        content = uiState.content,
                                        category = uiState.category,
                                        popular = uiState.popular,
                                        main = uiState.main,
                                        sponsored = uiState.sponsored
                                    )
                                )
                                if (result) {
                                    context.router.navigateTo(Screen.AdminSuccess.route)
                                }
                            } else {
                                uiState = uiState.copy(messagePopup = true)
                                delay(2000)
                                uiState = uiState.copy(messagePopup = false)
                            }
                        }
                    }
                )
            }
        }
    }
    if (uiState.messagePopup) {
        MessagePopup(
            message = "Please fill out all fields.",
            onDialogDismiss = { uiState = uiState.copy(messagePopup = false) }
        )
    }
    if (uiState.linkPopup) {
        ControlPopup(
            editorControl = EditorControl.Link,
            onDialogDismiss = { uiState = uiState.copy(linkPopup = false) },
            onAddClick = { href, _ ->
                applyStyle(ControlStyle.Link(selectedText = getSelectedText(), href = href))
            }
        )
    }
}