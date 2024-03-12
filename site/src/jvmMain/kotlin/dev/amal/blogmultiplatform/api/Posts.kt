package dev.amal.blogmultiplatform.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import dev.amal.blogmultiplatform.data.MongoDB
import dev.amal.blogmultiplatform.models.Constants.AUTHOR_PARAM
import dev.amal.blogmultiplatform.models.Constants.SKIP_PARAM
import dev.amal.blogmultiplatform.models.Post
import dev.amal.blogmultiplatform.util.getBody
import dev.amal.blogmultiplatform.util.setBody
import org.bson.codecs.ObjectIdGenerator

@Api(routeOverride = "addpost")
suspend fun addPost(
    context: ApiContext,
    database: MongoDB = context.data.getValue<MongoDB>()
) {
    try {
        val post = context.req.getBody<Post>()
        val newPost = post?.copy(_id = ObjectIdGenerator().generate().toString())
        context.res.setBody(newPost?.let { database.addPost(post = it) })
    } catch (exception: Exception) {
        context.res.setBody(exception.message)
    }
}

@Api(routeOverride = "readmyposts")
suspend fun readMyPosts(
    context: ApiContext,
    database: MongoDB = context.data.getValue<MongoDB>()
) {
    try {
        val skip = context.req.params[SKIP_PARAM]?.toIntOrNull() ?: 0
        val author = context.req.params[AUTHOR_PARAM] ?: ""
        context.res.setBody(database.readMyPosts(skip = skip, author = author))
    } catch (exception: Exception) {
        context.res.setBody(exception.message)
    }
}

@Api(routeOverride = "deleteselectedposts")
suspend fun deleteSelectedPosts(
    context: ApiContext,
    database: MongoDB = context.data.getValue<MongoDB>()
) {
    try {
        val request = context.req.getBody<List<String>>()
        context.res.setBody(request?.let { database.deleteSelectedPosts(ids = it) })
    } catch (exception: Exception) {
        context.res.setBody(exception.message)
    }
}