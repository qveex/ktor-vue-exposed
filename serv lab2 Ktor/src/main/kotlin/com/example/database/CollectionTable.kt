package com.example.database

import com.example.models.Collection
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object CollectionTable: IntIdTable() {

    val name = varchar("name", 32)
    val type = varchar("type", 32)
    val description = varchar("description", 255)
    val cost = integer("cost")
    val user = reference("user", UserTable)

}

class CollectionRow(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<CollectionRow>(CollectionTable)
    var name        by CollectionTable.name
    var type        by CollectionTable.type
    var description by CollectionTable.description
    var cost        by CollectionTable.cost
    var user        by UserRow referencedOn CollectionTable.user

    fun toSerializable() = Collection(id.value, name, type, description, cost, user.id.value)

}