package dev.amal.blogmultiplatform.models

import kotlinx.serialization.Serializable

@Serializable
data class UserWithoutPassword(
    val _id: String = "",
    val username: String = "",
)