package dev.amal.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.text.SpanText
import dev.amal.blogmultiplatform.models.Category
import dev.amal.blogmultiplatform.models.JsTheme
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

@Composable
fun CategoryChip(
    category: Category,
    darkTheme: Boolean = false
) {
    Box(
        modifier = Modifier
            .height(32.px)
            .padding(leftRight = 14.px)
            .borderRadius(r = 100.px)
            .border(width = 1.px, style = LineStyle.Solid, color = JsTheme.HalfBlack.rgb),
        contentAlignment = Alignment.Center
    ) {
        SpanText(
            modifier = Modifier
                .fontSize(12.px)
                .color(JsTheme.HalfBlack.rgb),
            text = category.name
        )
    }
}