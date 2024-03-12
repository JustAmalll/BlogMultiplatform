package dev.amal.blogmultiplatform.data

import dev.amal.blogmultiplatform.models.Post
import dev.amal.blogmultiplatform.models.User

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
    suspend fun checkUserId(id: String): Boolean

    suspend fun addPost(post: Post): Boolean
}