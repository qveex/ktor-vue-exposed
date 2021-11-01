package com.example.database.daos

import com.example.database.CollectionRow
import com.example.database.CollectionTable
import com.example.models.Collection
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class CollectionDao {

    suspend fun getAll() = newSuspendedTransaction {
        return@newSuspendedTransaction ArrayList<Collection>().apply { CollectionRow.all().toList().forEach { this.add(it.toSerializable()) } } //CollectionTable.selectAll().map { CollectionTable.toCollection(it) }
    }

    suspend fun get(id: Int) = newSuspendedTransaction {
        return@newSuspendedTransaction CollectionRow.findById(id)?.toSerializable()
    }

    suspend fun add(c: Collection) = newSuspendedTransaction {
        CollectionTable.insert {
            it[name] = c.name
            it[type] = c.type
            it[description] = c.description
            it[cost] = c.cost
            it[user] = c.userId
        }
        return@newSuspendedTransaction true
    }

    suspend fun delete(id: Int) = newSuspendedTransaction {
        val collection = CollectionRow.findById(id) ?: return@newSuspendedTransaction false
        collection.delete()
        return@newSuspendedTransaction true
    }

    suspend fun deleteAll() = newSuspendedTransaction {
        return@newSuspendedTransaction CollectionTable.deleteAll()
    }

    suspend fun update(id: Int, newC: Collection) = newSuspendedTransaction {
        get(id) ?: return@newSuspendedTransaction false
        CollectionTable.update({ CollectionTable.id eq id }) {
            it[name] = newC.name
            it[type] = newC.type
            it[description] = newC.description
            it[cost] = newC.cost
        }
        return@newSuspendedTransaction true
    }
}