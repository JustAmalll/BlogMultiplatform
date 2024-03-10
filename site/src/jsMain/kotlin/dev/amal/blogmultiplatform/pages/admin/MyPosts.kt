package dev.amal.blogmultiplatform.pages.admin

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import dev.amal.blogmultiplatform.components.AdminPageLayout
import dev.amal.blogmultiplatform.util.isUserLoggedIn

@Page
@Composable
fun MyPostsPage() {
    isUserLoggedIn {
        MyPostsScreen()
    }
}

@Composable
fun MyPostsScreen() {
    AdminPageLayout {

    }
}