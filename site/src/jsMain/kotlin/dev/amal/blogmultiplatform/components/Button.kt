package dev.amal.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import dev.amal.blogmultiplatform.models.JsTheme
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    bgColor: Color.Rgb = JsTheme.Primary.rgb,
    textColor: Color.Rgb = JsTheme.White.rgb,
    fontWeight: FontWeight = FontWeight.Medium,
    onClick: () -> Unit
) {
    Button(
        attrs = modifier
            .height(54.px)
            .padding(leftRight = 24.px)
            .borderRadius(r = 4.px)
            .border(0.px)
            .backgroundColor(bgColor)
            .color(textColor)
            .onClick { onClick() }
            .toAttrs()
    ) {
        SpanText(
            modifier = Modifier
                .fontWeight(fontWeight)
                .fontSize(14.px),
            text = text
        )
    }
}