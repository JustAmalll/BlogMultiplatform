package dev.amal.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
actual data class UserWithoutPassword(
    @SerialName(value = "_id") actual val id: String = "",
    @SerialName(value = "username") actual val username: String = "",
)