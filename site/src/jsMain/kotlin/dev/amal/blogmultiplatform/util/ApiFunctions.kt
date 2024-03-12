package dev.amal.blogmultiplatform.util

import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.browser.http.http
import dev.amal.blogmultiplatform.models.Constants.AUTHOR_PARAM
import dev.amal.blogmultiplatform.models.Constants.SKIP_PARAM
import dev.amal.blogmultiplatform.models.Post
import dev.amal.blogmultiplatform.models.PostWithoutDetails
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

suspend fun fetchRandomJoke(): RandomJoke = try {
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

suspend fun addPost(post: Post): Boolean = try {
    window.api.tryPost(
        apiPath = "addpost",
        body = Json.encodeToString(value = post).encodeToByteArray()
    )?.decodeToString().toBoolean()
} catch (e: Exception) {
    println(e.message)
    false
}

suspend fun fetchMyPosts(skip: Int): List<PostWithoutDetails> = try {
    window.api.tryGet(
        apiPath = buildString {
            append("readmyposts")
            append("?${SKIP_PARAM}=$skip")
            append("&${AUTHOR_PARAM}=${localStorage["username"]}")
        }
    )?.decodeToString().parseData()
} catch (exception: Exception) {
    println(exception.message)
    emptyList()
}

suspend fun deleteSelectedPosts(ids: List<String>): Boolean = try {
    window.api.tryPost(
        apiPath = "deleteselectedposts",
        body = Json.encodeToString(value = ids).encodeToByteArray()
    )?.decodeToString().toBoolean()
} catch (exception: Exception) {
    println(exception.message)
    false
}

inline fun <reified T> String?.parseData(): T =
    Json.decodeFromString(string = toString())