package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.database.daos.UserDao
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import java.security.MessageDigest

private fun hashCode(key: String): String {
    return MessageDigest
        .getInstance("SHA-256")
        .digest(key.toByteArray())
        .fold("") { str, it -> str + "%02x".format(it) }
}

fun Application.configureAuthentication() {
    install(Authentication) {

        val userDao = UserDao()

        basic("auth-basic-hashed") {
            validate { credentials ->

                val name = credentials.name
                val hash = hashCode(credentials.password)

                if (userDao.get(name)?.hash == hash)
                    UserIdPrincipal(credentials.name)
                else
                    null
            }
        }

        jwt("jwt") {
            verifier(
                JWT
                .require(Algorithm.HMAC256("secret"))
                .withAudience("http://0.0.0.0:8443/api")
                .withIssuer("https://0.0.0.0:8443/")
                .build())
            validate { credential ->
                val login = credential.payload.getClaim("login").asString()
                println("TRY USING TOKEN WITH LOGIN = $login")
                if (userDao.get(login) != null) JWTPrincipal(credential.payload)
                else null
            }
        }
    }

}