package com.example.database

import com.example.models.User
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object UserTable: IntIdTable() {

    val login = varchar("login", 32)
    val hash = varchar("hash", 64)

    fun toUser(row: ResultRow): User =
        User(
            login = row[login],
            hash = row[hash]
        )
}

class UserRow(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<UserRow>(UserTable)
    var login by UserTable.login
    var hash  by UserTable.hash
    val collections by CollectionRow referrersOn CollectionTable.user

    fun toUser() = User(id.value, login, hash)
}