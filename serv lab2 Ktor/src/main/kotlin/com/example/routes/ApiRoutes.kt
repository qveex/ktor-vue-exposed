package com.example.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.database.UserRow
import com.example.database.daos.CollectionDao
import com.example.database.daos.UserDao
import com.example.models.Collection
import com.example.models.User
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun Route.apiRouting() {

    val collectionDao = CollectionDao()
    val userDao = UserDao()

    route ("/api/collections") {


        get {
            val collections = collectionDao.getAll()
            if (collections.isNotEmpty()) call.respond(HttpStatusCode.OK, collections)
            else call.respondText("List of collections empty", status = HttpStatusCode.NoContent)
        }

        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            try { id.toInt() } catch (e: Exception) { return@get call.respondText(
                "Wrong id: $id",
                status = HttpStatusCode.BadRequest)
            }
            val collection = collectionDao.get(id.toInt()) ?: return@get call.respondText(
                "No collection with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(status = HttpStatusCode.OK, collection)
        }

        //authenticate("auth-basic-hashed") {
        authenticate("jwt") {

            post {
                val collection = call.receive<Collection>()
                if (collection.cost < 0 ||
                    collection.description.isEmpty() ||
                    collection.name.isEmpty() ||
                    collection.type.isEmpty())
                    return@post call.respondText(
                        "The data is incorrect",
                        status = HttpStatusCode.BadRequest
                    )
                return@post if (collectionDao.add(collection)) call.respondText("The new collection was created", status = HttpStatusCode.Created)
                else call.respondText("Wrong parameters of collections is already exist", status = HttpStatusCode.Created)
            }

            delete("{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                try { id.toInt() } catch (e: Exception) { return@delete call.respondText(
                    "Wrong id: $id",
                    status = HttpStatusCode.BadRequest)
                }
                if (collectionDao.delete(id.toInt())) call.respondText("The collection was deleted", status = HttpStatusCode.Accepted)
                else call.respondText("The collection does not exist", status = HttpStatusCode.NotFound)
            }

            put("{id}") {
                val id = call.parameters["id"] ?: return@put call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                try { id.toInt() } catch (e: Exception) { return@put call.respondText(
                    "Wrong id: $id",
                    status = HttpStatusCode.BadRequest)
                }
                if (collectionDao.update(id.toInt(), call.receive()))
                    call.respondText("The collection is saved", status = HttpStatusCode.OK)
                else
                    call.respondText("The collection does not exist", status = HttpStatusCode.BadRequest)
            }
        }
    }

    route("api/users") {
        authenticate("jwt") {
            get("{login}") {
                val login = call.parameters["login"] ?: return@get call.respondText(
                    "Missing or malformed login",
                    status = HttpStatusCode.BadRequest
                )
                val collections = userDao.getUserCollections(login)
                if (collections.isNotEmpty()) call.respond(HttpStatusCode.OK, collections)
                else call.respondText("List of collections empty", status = HttpStatusCode.NoContent)
            }
        }
    }

    // open routing for user login and registration
    route("") {

        post("/login") {
            val user = call.receive<User>()

            if (userDao.get(user.login)?.hash == user.hash) {
                val token = createToken(user)
                val id = userDao.get(user.login)?.id
                call.respond(hashMapOf("token" to token, "id" to id.toString()))
            }
            else call.respondText("Wrong login or password", status = HttpStatusCode.BadRequest)
        }


        post("/registration") {
            val newUser = call.receive<User>()
            return@post if(userDao.add(newUser)) {
                val token = createToken(newUser)
                val id = userDao.get(newUser.login)?.id
                call.respond(hashMapOf("token" to token, "id" to id.toString()))
            }
            else call.respondText("User is already exist", status = HttpStatusCode.BadRequest)
        }
    }
}




fun createToken(user: User): String = JWT.create()
    .withAudience("http://0.0.0.0:8443/api")
    .withIssuer("https://0.0.0.0:8443/")
    .withClaim("login", user.login)
    .withExpiresAt(Date(System.currentTimeMillis() + 6000000))
    .sign(Algorithm.HMAC256("secret"))