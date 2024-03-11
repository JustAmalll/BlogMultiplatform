package dev.amal.blogmultiplatform.styles

import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.focus
import dev.amal.blogmultiplatform.models.JsTheme
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val LoginInputStyle by ComponentStyle {
    base {
        Modifier
            .outline(0.px)
            .border(
                width = 2.px,
                style = LineStyle.Solid,
                color = Colors.Transparent
            )
            .transition(CSSTransition(property = "border", duration = 300.ms))
    }
    focus {
        Modifier
            .outline(0.px)
            .border(
            width = 2.px,
            style = LineStyle.Solid,
            color = JsTheme.Primary.rgb
        )
    }
}