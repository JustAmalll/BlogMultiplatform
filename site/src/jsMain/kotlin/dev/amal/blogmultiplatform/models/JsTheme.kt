package dev.amal.blogmultiplatform.models

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.rgb
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.rgba

enum class JsTheme(val rgb: Color.Rgb) {
    Primary(rgb = rgb(r = 0, g = 162, b = 255)),
    Secondary(rgb = rgb(r = 0, g = 16, b = 25)),
    Tertiary(rgb = rgb(r = 0, g = 25, b = 37)),
    LightGray(rgb = rgb(r = 250, g = 250, b = 250)),
    Gray(rgb = rgb(r = 233, g = 233, b = 233)),
    DarkGray(rgb = rgb(r = 100, g = 100, b = 100)),
    HalfWhite(rgb = rgba(r = 255, g = 255, b = 255, a = 0.5f)),
    HalfBlack(rgb = rgba(r = 0, g = 0, b = 0, a = 0.5f)),
    White(rgb = rgb(r = 255, g = 255, b = 255)),
    Green(rgb = rgb(r = 0, g = 255, b = 148)),
    Yellow(rgb = rgb(r = 255, g = 236, b = 69)),
    Red(rgb = rgb(r = 255, g = 99, b = 89)),
    Purple(rgb = rgb(r = 139, g = 109, b = 255)),
    Sponsored(rgb = rgb(r = 51, g = 0, b = 255))
}