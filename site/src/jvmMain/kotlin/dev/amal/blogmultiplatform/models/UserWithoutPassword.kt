package dev.amal.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator

@Serializable
data class UserWithoutPassword(
    @SerialName(value = "_id") val id: String = ObjectIdGenerator().generate().toString(),
    @SerialName(value = "username") val username: String = "",
)