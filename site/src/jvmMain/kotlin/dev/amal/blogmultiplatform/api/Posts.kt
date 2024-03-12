package dev.amal.blogmultiplatform.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import dev.amal.blogmultiplatform.data.MongoDB
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
