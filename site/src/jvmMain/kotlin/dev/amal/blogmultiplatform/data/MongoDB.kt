package dev.amal.blogmultiplatform.data

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import dev.amal.blogmultiplatform.models.User
import dev.amal.blogmultiplatform.util.Constants.DATABASE_NAME
import kotlinx.coroutines.flow.firstOrNull

@InitApi
fun initMongoDB(context: InitApiContext) {
    System.setProperty(
        "org.litote.mongo.test.mapping.service",
        "org.litote.kmongo.serialization.SerializationClassMappingTypeService"
    )
    context.data.add(value = MongoDB(context = context))
}

class MongoDB(private val context: InitApiContext) : MongoRepository {
    private val client = MongoClient.create(System.getenv("MONGODB_URI"))
    private val database = client.getDatabase(DATABASE_NAME)
    private val userCollection = database.getCollection<User>("user")

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
}