package dev.amal.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.silk.components.forms.Switch
import com.varabyte.kobweb.silk.components.forms.SwitchSize
import com.varabyte.kobweb.silk.components.text.SpanText
import dev.amal.blogmultiplatform.models.JsTheme
import org.jetbrains.compose.web.css.px

@Composable
fun LabeledSwitch(
    modifier: Modifier = Modifier,
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    size: SwitchSize = SwitchSize.LG,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Switch(
            modifier = Modifier.margin(right = 8.px),
            checked = checked,
            onCheckedChange = onCheckedChange,
            size = size
        )
        SpanText(
            modifier = Modifier
                .fontSize(14.px)
                .color(JsTheme.HalfBlack.rgb),
            text = text
        )
    }
}