package com.example.database.daos

import com.example.database.UserRow
import com.example.database.UserTable
import com.example.models.Collection
import com.example.models.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class UserDao {
    suspend fun getAll() = newSuspendedTransaction {
        return@newSuspendedTransaction UserTable.selectAll().map { UserTable.toUser(it) }
    }

    suspend fun get(id: Int) = newSuspendedTransaction {
        val u = UserTable.select { UserTable.id eq id }.map { UserTable.toUser(it) }
        return@newSuspendedTransaction if (u.isEmpty()) null
        else u.first()
    }

    suspend fun getUserCollections(login: String) = newSuspendedTransaction {
        val user = UserRow.find { UserTable.login eq login }
        if (user.empty()) return@newSuspendedTransaction emptyList<Collection>()
        return@newSuspendedTransaction ArrayList<Collection>().apply { user.first().collections.forEach { this.add(it.toSerializable()) } }
    }

    suspend fun get(login: String) = newSuspendedTransaction {
        val u = UserRow.find { UserTable.login eq login }
        return@newSuspendedTransaction if (u.empty()) null
        else u.first().toUser()
    }

    suspend fun add(u: User) = newSuspendedTransaction {
        if (get(u.login) != null) false
        else {
            UserTable.insert {
                it[login] = u.login
                it[hash] = u.hash
            }
            return@newSuspendedTransaction true
        }
    }

    suspend fun delete(id: Int) = newSuspendedTransaction {
        val c = UserTable.select { UserTable.id eq id }.map { UserTable.toUser(it) }
        return@newSuspendedTransaction if (c.isEmpty()) false
        else {
            UserTable.deleteWhere { UserTable.id eq id }
            true
        }
    }

    suspend fun deleteAll() = newSuspendedTransaction {
        return@newSuspendedTransaction UserTable.deleteAll()
    }

    suspend fun update(id: Int, newU: User) = newSuspendedTransaction {
        get(id) ?: return@newSuspendedTransaction false
        UserTable.update({ UserTable.id eq id }) {
            it[login] = newU.login
            it[hash] = newU.hash
        }
        return@newSuspendedTransaction true
    }
}