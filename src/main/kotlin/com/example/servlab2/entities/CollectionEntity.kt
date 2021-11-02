package com.example.servlab2.entities

import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class CollectionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String = "",
    var type: String = "",
    var description: String = "",
    var cost: Int = 0
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}