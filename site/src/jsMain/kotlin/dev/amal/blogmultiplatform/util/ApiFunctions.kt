package dev.amal.blogmultiplatform.util

import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.browser.http.http
import dev.amal.blogmultiplatform.models.RandomJoke
import dev.amal.blogmultiplatform.models.User
import dev.amal.blogmultiplatform.models.UserWithoutPassword
import dev.amal.blogmultiplatform.util.Constants.HUMOR_API_URL
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.get
import org.w3c.dom.set
import kotlin.js.Date
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes

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

suspend fun fetchRandomJoke(): RandomJoke? = try {
    val date = localStorage["date"]
    val savedJoke = localStorage["joke"]

    val currentTime = Date.now()
    val dayHasPassed = date != null && currentTime - date.toDouble() >= 24.hours.inWholeMilliseconds

    if (dayHasPassed || savedJoke == null) {
        val result = window.http.get(HUMOR_API_URL).decodeToString()
        localStorage["date"] = currentTime.toString()
        localStorage["joke"] = result

        result.parseData()
    } else {
        savedJoke.parseData<RandomJoke>()
    }
} catch (exception: Exception) {
    println(exception.message)
    RandomJoke(id = -1, joke = exception.message.toString())
}

inline fun <reified T> String?.parseData(): T =
    Json.decodeFromString(string = toString())