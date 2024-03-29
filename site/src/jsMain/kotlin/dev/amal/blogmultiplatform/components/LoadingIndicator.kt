package dev.amal.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.role
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(topBottom = 50.px),
        contentAlignment = Alignment.Center
    ) {
        Div(
            attrs = Modifier
                .classNames("spinner-border", "text-primary")
                .role("status")
                .toAttrs()
        ) {
            Span(
                attrs = Modifier
                    .classNames("sr-only")
                    .toAttrs()
            ) {
                Text("Loading...")
            }
        }
    }
}