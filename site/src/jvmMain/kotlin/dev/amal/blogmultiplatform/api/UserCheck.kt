package dev.amal.blogmultiplatform.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.setBodyText
import dev.amal.blogmultiplatform.data.MongoDB
import dev.amal.blogmultiplatform.models.User
import dev.amal.blogmultiplatform.models.UserWithoutPassword
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@Api(routeOverride = "usercheck")
suspend fun userCheck(
    context: ApiContext,
    database: MongoDB = context.data.getValue<MongoDB>()
) {
    try {
        val userRequest = context.req.body?.decodeToString()?.let {
            Json.decodeFromString<User>(it)
        }
        val user = userRequest?.let {
            database.checkUserExistence(
                User(
                    username = it.username,
                    password = hashPassword(password = it.password)
                )
            )
        }
        if (user != null) {
            context.res.setBodyText(
                Json.encodeToString<UserWithoutPassword>(
                    UserWithoutPassword(
                        _id = user._id,
                        username = user.username
                    )
                )
            )
        } else {
            context.res.setBodyText(text = Json.encodeToString(value = "User doesn't exist."))
        }
    } catch (exception: Exception) {
        context.res.setBodyText(text = Json.encodeToString(value = exception.message))
    }
}

@Api(routeOverride = "checkuserid")
suspend fun checkUserId(
    context: ApiContext,
    database: MongoDB = context.data.getValue<MongoDB>()
) {
    try {
        val idRequest = context.req.body?.decodeToString()?.let {
            Json.decodeFromString<String>(it)
        }
        val result = idRequest?.let { database.checkUserId(it) }
        context.res.setBodyText(Json.encodeToString(value = result ?: false))
    } catch (exception: Exception) {
        context.res.setBodyText(Json.encodeToString(value = false))
    }
}

private fun hashPassword(password: String): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    val hashBytes = messageDigest.digest(password.toByteArray(StandardCharsets.UTF_8))
    val hexString = StringBuffer()

    for (byte in hashBytes) {
        hexString.append(String.format("%02x", byte))
    }

    return hexString.toString()
}