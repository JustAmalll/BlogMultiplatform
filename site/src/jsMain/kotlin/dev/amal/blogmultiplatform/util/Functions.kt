package dev.amal.blogmultiplatform.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.core.rememberPageContext
import kotlinx.browser.localStorage
import org.w3c.dom.get

@Composable
fun isUserLoggedIn(content: @Composable () -> Unit) {
    val context = rememberPageContext()
    var isUserExists by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        val userId = localStorage["userId"]
        isUserExists = !userId.isNullOrEmpty() && checkUserId(id = userId)

        if (!isUserExists) {
            context.router.navigateTo("/admin/login")
        }
    }

    if (isUserExists) {
        content()
    }
}