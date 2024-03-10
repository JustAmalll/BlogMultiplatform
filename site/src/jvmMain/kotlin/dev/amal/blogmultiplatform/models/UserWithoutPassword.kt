package dev.amal.blogmultiplatform.models

import kotlinx.serialization.Serializable
import org.bson.codecs.ObjectIdGenerator

@Serializable
actual data class UserWithoutPassword(
    actual val _id: String = ObjectIdGenerator().generate().toString(),
    actual val username: String = "",
)