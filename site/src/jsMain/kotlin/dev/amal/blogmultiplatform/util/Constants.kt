package dev.amal.blogmultiplatform.util

object Constants {
    const val SIDE_PANEL_WIDTH = 250
    const val COLLAPSED_PANEL_HEIGHT = 100

    const val HUMOR_API_URL =
        "https://api.humorapi.com/jokes/random?api-key=f0cac1365ead42e58b2ee94684b45b56&max-length=180"
}

object Res {
    object Image {
        const val LOGO = "/logo.svg"
        const val LAUGH = "/laugh.png"
    }

    object Icon {
        const val HOME = "/home.svg"
        const val CREATE = "/create.svg"
        const val POSTS = "/posts.svg"
        const val LOGOUT = "/logout.svg"

        const val BOLD = "/bold.svg"
        const val ITALIC = "/italic.svg"
        const val LINK = "/link.svg"
        const val TITLE = "/title.svg"
        const val SUBTITLE = "/subtitle.svg"
        const val QUOTE = "/quote.svg"
        const val CODE = "/code.svg"
        const val IMAGE = "/image.svg"
        const val CHECKMARK = "/checkmark.svg"
    }
}

object Id {
    const val EDITOR = "editor"
    const val EDITOR_PREVIEW = "editor_preview"
}