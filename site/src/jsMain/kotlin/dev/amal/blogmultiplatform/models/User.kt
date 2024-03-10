package dev.amal.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
actual data class User(
    @SerialName(value = "_id") actual val id: String = "",
    @SerialName(value = "username") actual val username: String = "",
    @SerialName(value = "password") actual val password: String = ""
)