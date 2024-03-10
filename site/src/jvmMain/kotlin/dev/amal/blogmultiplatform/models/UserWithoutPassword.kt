package dev.amal.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator

@Serializable
actual data class UserWithoutPassword(
    @SerialName(value = "_id") actual val id: String = ObjectIdGenerator().generate().toString(),
    @SerialName(value = "username") actual val username: String = "",
)