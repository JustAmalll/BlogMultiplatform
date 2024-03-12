package dev.amal.blogmultiplatform.util

import com.varabyte.kobweb.api.http.Request
import com.varabyte.kobweb.api.http.Response
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T> Response.setBody(data: T) {
    setBodyText(text = Json.encodeToString(value = data))
}

inline fun <reified T> Request.getBody(): T? =
    body?.decodeToString()?.let { Json.decodeFromString(it) }