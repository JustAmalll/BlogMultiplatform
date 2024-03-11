package dev.amal.blogmultiplatform.models

import kotlinx.serialization.Serializable

@Serializable
enum class Category(val color: String) {
    Technology(color = JsTheme.Green.hex),
    Programming(color = JsTheme.Yellow.hex),
    Design(color = JsTheme.Purple.hex)
}