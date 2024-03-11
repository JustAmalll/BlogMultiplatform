package dev.amal.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import dev.amal.blogmultiplatform.components.Input
import dev.amal.blogmultiplatform.models.JsTheme
import dev.amal.blogmultiplatform.models.User
import dev.amal.blogmultiplatform.util.checkUserExistence
import kotlinx.browser.localStorage
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.w3c.dom.set

@Page
@Composable
fun LoginScreen() {
    val scope = rememberCoroutineScope()
    val context = rememberPageContext()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(leftRight = 50.px, top = 80.px, bottom = 24.px)
                .backgroundColor(JsTheme.LightGray.rgb),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .margin(bottom = 50.px)
                    .width(100.px),
                src = "/logo.svg"
            )
            Input(
                modifier = Modifier
                    .width(350.px)
                    .margin(bottom = 24.px),
                value = username,
                onValueChanged = {
                    username = it
                    errorText = null
                },
                placeholder = "Username",
                backgroundColor = Colors.White
            )
            Input(
                modifier = Modifier
                    .width(350.px)
                    .margin(bottom = 24.px),
                value = password,
                onValueChanged = {
                    password = it
                    errorText = null
                },
                placeholder = "Password",
                type = InputType.Password,
                backgroundColor = Colors.White
            )
            Button(
                attrs = Modifier
                    .margin(bottom = 24.px)
                    .width(350.px)
                    .height(54.px)
                    .backgroundColor(JsTheme.Primary.rgb)
                    .color(Colors.White)
                    .borderRadius(r = 4.px)
                    .fontWeight(FontWeight.Medium)
                    .fontSize(14.px)
                    .border(width = 0.px)
                    .cursor(Cursor.Pointer)
                    .onClick {
                        scope.launch {
                            if (username.isNotEmpty() && password.isNotEmpty()) {
                                val user = checkUserExistence(
                                    user = User(username = username, password = password)
                                )
                                if (user != null) {
                                    localStorage["userId"] = user._id
                                    localStorage["username"] = user.username
                                    context.router.navigateTo("/admin")
                                } else {
                                    errorText = "The user doesn't exist."
                                }
                            } else {
                                errorText = "Input fields are empty."
                            }
                        }
                    }
                    .toAttrs()
            ) {
                SpanText(text = "Sign in")
            }
            SpanText(
                modifier = Modifier
                    .width(350.px)
                    .color(Colors.Red)
                    .textAlign(TextAlign.Center),
                text = errorText ?: " "
            )
        }
    }
}