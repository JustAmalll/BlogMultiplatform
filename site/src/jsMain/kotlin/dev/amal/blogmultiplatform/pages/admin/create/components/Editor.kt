package dev.amal.blogmultiplatform.pages.admin.create.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Resize
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.resize
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.toModifier
import dev.amal.blogmultiplatform.models.JsTheme
import dev.amal.blogmultiplatform.styles.LoginInputStyle
import dev.amal.blogmultiplatform.util.Id
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.TextArea

@Composable
fun Editor(editorVisibility: Boolean) {

    if (editorVisibility) {
        TextArea(
            attrs = LoginInputStyle
                .toModifier()
                .id(Id.EDITOR)
                .fillMaxWidth()
                .height(400.px)
                .resize(Resize.None)
                .margin(top = 8.px)
                .padding(all = 20.px)
                .backgroundColor(JsTheme.LightGray.rgb)
                .borderRadius(r = 4.px)
                .fontSize(16.px)
                .toAttrs { attr("placeholder", "Type here...") }
        )
    } else {
        Div(
            attrs = Modifier
                .fillMaxWidth()
                .height(400.px)
                .maxHeight(400.px)
                .margin(top = 8.px)
                .padding(all = 20.px)
                .backgroundColor(JsTheme.LightGray.rgb)
                .borderRadius(r = 4.px)
                .overflow(Overflow.Auto)
                .scrollBehavior(ScrollBehavior.Smooth)
                .toAttrs()
        )
    }
}
