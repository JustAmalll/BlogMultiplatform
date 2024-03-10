package dev.amal.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator

@Serializable
data class User(
    @SerialName(value = "_id") val id: String = ObjectIdGenerator().generate().toString(),
    @SerialName(value = "username") val username: String = "",
    @SerialName(value = "password") val password: String = ""
)