package dev.amal.blogmultiplatform.models

import kotlinx.serialization.Serializable

@Serializable
enum class Category(val color: String) {
    Technology(color = "#00FF94"),
    Programming(color = "#FFEC45"),
    Design(color = "#8B6DFF")
}