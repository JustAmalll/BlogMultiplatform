package dev.amal.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import dev.amal.blogmultiplatform.models.JsTheme
import dev.amal.blogmultiplatform.navigation.Screen
import dev.amal.blogmultiplatform.util.Res
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun SuccessPage() {
    val context = rememberPageContext()

    LaunchedEffect(Unit) {
        delay(2000)
        context.router.navigateTo(Screen.AdminCreate.route)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.margin(bottom = 24.px),
            src = Res.Icon.CHECKMARK,
            alt = "Checkmark Icon"
        )
        SpanText(
            modifier = Modifier.fontSize(24.px),
            text = "Post Successfully Created!"
        )
        SpanText(
            modifier = Modifier
                .color(JsTheme.HalfBlack.rgb)
                .fontSize(18.px),
            text = "Redirecting you back..."
        )
    }
}