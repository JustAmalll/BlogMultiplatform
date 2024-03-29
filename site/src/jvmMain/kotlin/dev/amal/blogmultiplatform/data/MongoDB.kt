package dev.amal.blogmultiplatform.data

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Indexes.descending
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import dev.amal.blogmultiplatform.models.Constants.POSTS_PER_PAGE
import dev.amal.blogmultiplatform.models.Post
import dev.amal.blogmultiplatform.models.PostWithoutDetails
import dev.amal.blogmultiplatform.models.User
import dev.amal.blogmultiplatform.util.Constants.DATABASE_NAME
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList

@InitApi
fun initMongoDB(context: InitApiContext) {
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(value = MongoDB(context = context))
}

class MongoDB(private val context: InitApiContext) : MongoRepository {
    private val client = MongoClient.create()
    private val database = client.getDatabase(DATABASE_NAME)
    private val userCollection = database.getCollection<User>("user")
    private val postCollection = database.getCollection<Post>("post")

    override suspend fun checkUserExistence(user: User): User? = try {
        userCollection.find(
            Filters.and(
                Filters.eq(User::username.name, user.username),
                Filters.eq(User::password.name, user.password)
            )
        ).firstOrNull()
    } catch (exception: Exception) {
        context.logger.error(message = exception.message.toString())
        null
    }

    override suspend fun checkUserId(id: String): Boolean = try {
        userCollection.find<User>(Filters.eq(User::_id.name, id)).firstOrNull() != null
    } catch (exception: Exception) {
        context.logger.error(message = exception.message.toString())
        false
    }

    override suspend fun addPost(post: Post): Boolean =
        postCollection.insertOne(post).wasAcknowledged()

    override suspend fun readSelectedPost(id: String): Post? =
        postCollection.find(Filters.eq(Post::_id.name, id)).firstOrNull()

    override suspend fun readMyPosts(
        skip: Int,
        author: String
    ): List<PostWithoutDetails> = postCollection
        .withDocumentClass(PostWithoutDetails::class.java)
        .find(Filters.eq(PostWithoutDetails::author.name, author))
        .sort(descending(PostWithoutDetails::date.name))
        .skip(skip)
        .limit(POSTS_PER_PAGE)
        .toList()

    override suspend fun deleteSelectedPosts(ids: List<String>): Boolean =
        postCollection.deleteMany(Filters.`in`(Post::_id.name, ids)).wasAcknowledged()
}