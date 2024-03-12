package dev.amal.blogmultiplatform.navigation

sealed class Screen(val route: String) {
    data object AdminHome : Screen(route = "/admin/")
    data object AdminLogin : Screen(route = "/admin/login")
    data object AdminCreate : Screen(route = "/admin/create")
    data object AdminMyPosts : Screen(route = "/admin/myposts")
    data object AdminSuccess : Screen(route = "/admin/success")
}