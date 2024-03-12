package dev.amal.blogmultiplatform.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.disabled
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.forms.Input
import com.varabyte.kobweb.silk.components.forms.UnstyledInputVariant
import com.varabyte.kobweb.silk.components.style.toModifier
import dev.amal.blogmultiplatform.models.JsTheme
import dev.amal.blogmultiplatform.styles.LoginInputStyle
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.px

@Composable
fun Input(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String? = null,
    type: InputType.InputTypeWithStringValue = InputType.Text,
    backgroundColor: Color.Rgb = JsTheme.LightGray.rgb,
    borderRadius: CSSSizeValue<CSSUnit.px> = 4.px,
    paddingLeft: CSSSizeValue<CSSUnit.px> = 20.px,
    paddingRight: CSSSizeValue<CSSUnit.px> = 20.px,
    disabled: Boolean = false
) {
    Input(
        modifier = LoginInputStyle
            .toModifier()
            .then(modifier)
            .height(54.px)
            .borderRadius(r = borderRadius)
            .padding(left = paddingLeft, right = paddingRight)
            .backgroundColor(backgroundColor)
            .fontSize(14.px)
            .thenIf(condition = disabled, other = Modifier.disabled()),
        type = type,
        value = value,
        onValueChanged = onValueChanged,
        placeholder = placeholder,
        variant = UnstyledInputVariant
    )
}