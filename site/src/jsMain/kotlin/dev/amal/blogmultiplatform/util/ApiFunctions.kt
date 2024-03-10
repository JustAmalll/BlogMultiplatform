package dev.amal.blogmultiplatform.util

import com.varabyte.kobweb.browser.api
import dev.amal.blogmultiplatform.models.User
import dev.amal.blogmultiplatform.models.UserWithoutPassword
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun checkUserExistence(user: User): UserWithoutPassword? = try {
    window.api.tryPost(
        apiPath = "usercheck",
        body = Json.encodeToString(value = user).encodeToByteArray()
    )?.decodeToString().parseData()
} catch (exception: Exception) {
    println(exception.message)
    null
}

suspend fun checkUserId(id: String): Boolean = try {
    window.api.tryPost(
        apiPath = "checkuserid",
        body = Json.encodeToString(value = id).encodeToByteArray()
    )?.decodeToString().parseData()
} catch (exception: Exception) {
    println(exception.message)
    false
}

inline fun <reified T> String?.parseData(): T =
    Json.decodeFromString(string = toString())