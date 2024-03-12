package dev.amal.blogmultiplatform.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onFocusIn
import com.varabyte.kobweb.compose.ui.modifiers.onFocusOut
import com.varabyte.kobweb.compose.ui.modifiers.onKeyDown
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.icons.fa.FaMagnifyingGlass
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import dev.amal.blogmultiplatform.models.JsTheme
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

@Composable
fun SearchBar(
    breakpoint: Breakpoint,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    onEnterClick: () -> Unit,
    onSearchIconClick: (Boolean) -> Unit
) {
    var focused by remember { mutableStateOf(false) }

    LaunchedEffect(breakpoint) {
        if (breakpoint >= Breakpoint.SM) {
            onSearchIconClick(false)
        }
    }

    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        Input(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusIn { focused = true }
                .onFocusOut { focused = false }
                .onKeyDown { if (it.key == "Enter") onEnterClick() },
            value = "",
            onValueChanged = {},
            placeholder = "Search...",
            borderRadius = 100.px,
            paddingLeft = 40.px
        )
        FaMagnifyingGlass(
            modifier = Modifier
                .margin(left = 14.px)
                .color(if (focused) JsTheme.Primary.rgb else JsTheme.DarkGray.rgb)
                .transition(CSSTransition(property = "color", duration = 200.ms)),
            size = IconSize.SM
        )
    }
}