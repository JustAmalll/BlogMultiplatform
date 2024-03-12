package dev.amal.blogmultiplatform.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import dev.amal.blogmultiplatform.data.MongoDB
import dev.amal.blogmultiplatform.models.User
import dev.amal.blogmultiplatform.models.UserWithoutPassword
import dev.amal.blogmultiplatform.util.getBody
import dev.amal.blogmultiplatform.util.setBody
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@Api(routeOverride = "usercheck")
suspend fun userCheck(
    context: ApiContext,
    database: MongoDB = context.data.getValue<MongoDB>()
) {
    try {
        val userRequest = context.req.getBody<User>()

        val user = userRequest?.let {
            database.checkUserExistence(
                User(
                    username = it.username,
                    password = hashPassword(password = it.password)
                )
            )
        }
        context.res.setBody(
            if (user != null) UserWithoutPassword(_id = user._id, username = user.username)
            else "User doesn't exist."
        )
    } catch (exception: Exception) {
        context.res.setBody(data = exception.message)
    }
}

@Api(routeOverride = "checkuserid")
suspend fun checkUserId(
    context: ApiContext,
    database: MongoDB = context.data.getValue<MongoDB>()
) {
    try {
        val idRequest = context.req.getBody<String>()
        context.res.setBody(data = idRequest?.let { database.checkUserId(it) } ?: false)
    } catch (exception: Exception) {
        context.res.setBody(data = false)
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