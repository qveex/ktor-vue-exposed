package com.example

import com.example.database.CollectionTable
import com.example.database.UserTable
import com.example.plugins.configureAuthentication
import com.example.plugins.configureHTTP
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.application.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction


fun main(args: Array<String>): Unit = EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    Database.connect("jdbc:h2:./Collections", "org.h2.Driver")
    Database.connect("jdbc:h2:./Users", "org.h2.Driver")
    transaction {
        SchemaUtils.create(CollectionTable)
        SchemaUtils.create(UserTable)
    }

    configureHTTP()
    configureSerialization()
    configureAuthentication()
    configureRouting()
}