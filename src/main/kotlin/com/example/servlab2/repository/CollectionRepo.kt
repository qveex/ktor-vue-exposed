package com.example.servlab2.repository

import com.example.servlab2.entities.CollectionEntity
import org.springframework.data.repository.CrudRepository

interface CollectionRepo: CrudRepository<CollectionEntity, Int> {

    fun findByName(name: String): List<CollectionEntity>

}