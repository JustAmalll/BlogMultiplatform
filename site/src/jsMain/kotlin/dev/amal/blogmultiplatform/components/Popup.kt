package dev.amal.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.silk.components.text.SpanText
import dev.amal.blogmultiplatform.models.JsTheme
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px

@Composable
fun MessagePopup(
    message: String,
    onDialogDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .position(Position.Fixed),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .backgroundColor(JsTheme.HalfBlack.rgb)
                .onClick { onDialogDismiss() }
        )
        Box(
            modifier = Modifier
                .padding(all = 24.px)
                .backgroundColor(Colors.White)
                .borderRadius(r = 4.px)
        ) {
            SpanText(
                modifier = Modifier
                    .fillMaxWidth()
                    .textAlign(TextAlign.Center)
                    .fontSize(16.px),
                text = message
            )
        }
    }
}