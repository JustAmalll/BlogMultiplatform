package dev.amal.blogmultiplatform.pages.admin.create.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import dev.amal.blogmultiplatform.components.Button
import dev.amal.blogmultiplatform.models.EditorControl
import dev.amal.blogmultiplatform.models.JsTheme
import dev.amal.blogmultiplatform.styles.EditorKeyStyle
import dev.amal.blogmultiplatform.util.Id
import dev.amal.blogmultiplatform.util.applyControlStyle
import kotlinx.browser.document
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLTextAreaElement

@Composable
fun EditorControls(
    breakpoint: Breakpoint,
    editorVisibility: Boolean,
    onLinkClick: () -> Unit,
    onImageClick: () -> Unit,
    onEditorVisibilityChange: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        SimpleGrid(
            modifier = Modifier.fillMaxWidth(),
            numColumns = numColumns(base = 1, sm = 2)
        ) {
            Row(
                modifier = Modifier
                    .backgroundColor(JsTheme.LightGray.rgb)
                    .borderRadius(r = 4.px)
                    .height(54.px)
            ) {
                EditorControl.entries.forEach {
                    EditorControlView(
                        control = it,
                        onClick = {
                            val editor = document.getElementById(Id.EDITOR)
                            val editorPreview = document.getElementById(Id.EDITOR_PREVIEW)

                            if (editor != null && editorPreview != null) {
                                applyControlStyle(
                                    editor = editor as HTMLTextAreaElement,
                                    editorPreview = editorPreview,
                                    editorControl = it,
                                    onLinkClick = onLinkClick,
                                    onImageClick = onImageClick
                                )
                            }
                        }
                    )
                }
            }
            Box(contentAlignment = Alignment.CenterEnd) {
                Button(
                    modifier = Modifier
                        .thenIf(
                            condition = breakpoint < Breakpoint.SM,
                            other = Modifier.fillMaxWidth()
                        )
                        .margin(topBottom = if (breakpoint < Breakpoint.SM) 12.px else 0.px),
                    text = "Preview",
                    bgColor = if (editorVisibility) JsTheme.LightGray.rgb else JsTheme.Primary.rgb,
                    textColor = if (editorVisibility) JsTheme.DarkGray.rgb else Colors.White,
                    onClick = { onEditorVisibilityChange() }
                )
            }
        }
    }
}

@Composable
private fun EditorControlView(
    control: EditorControl,
    onClick: () -> Unit
) {
    Box(
        modifier = EditorKeyStyle.toModifier()
            .fillMaxHeight()
            .padding(leftRight = 12.px)
            .borderRadius(r = 4.px)
            .cursor(Cursor.Pointer)
            .onClick { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            src = control.icon,
            alt = "${control.name} Icon"
        )
    }
}