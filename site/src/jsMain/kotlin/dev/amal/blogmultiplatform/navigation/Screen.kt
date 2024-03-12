package dev.amal.blogmultiplatform.navigation

import dev.amal.blogmultiplatform.models.Constants.POST_ID_PARAM

sealed class Screen(val route: String) {
    data object AdminHome : Screen(route = "/admin/")
    data object AdminLogin : Screen(route = "/admin/login")
    data object AdminCreate : Screen(route = "/admin/create") {
        fun passPostId(id: String) = "/admin/create?${POST_ID_PARAM}=$id"
    }
    data object AdminMyPosts : Screen(route = "/admin/myposts")
    data object AdminSuccess : Screen(route = "/admin/success")
}