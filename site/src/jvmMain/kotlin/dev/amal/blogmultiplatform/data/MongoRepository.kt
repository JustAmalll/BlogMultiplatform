package dev.amal.blogmultiplatform.data

import dev.amal.blogmultiplatform.models.User

interface MongoRepository {
    suspend fun checkUserExistence(user: User): User?
}