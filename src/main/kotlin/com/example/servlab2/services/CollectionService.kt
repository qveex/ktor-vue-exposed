package com.example.servlab2.services

import com.example.servlab2.entities.CollectionEntity
import com.example.servlab2.repository.CollectionRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CollectionService {

    @Autowired
    private lateinit var collectionRepo: CollectionRepo

    fun createCollection(collection: CollectionEntity): ResponseEntity<String> {
        return if (collectionRepo.existsById(collection.id)) ResponseEntity("The collection is already exist", HttpStatus.BAD_REQUEST)
        else {
            collectionRepo.save(collection)
            ResponseEntity("The new collection was created", HttpStatus.CREATED)
        }
    }

    fun getAllCollections(): ResponseEntity<String> {
        val list = collectionRepo.findAll().toList()
        return if (list.isEmpty()) ResponseEntity("List of collections empty", HttpStatus.NO_CONTENT)
        else ResponseEntity(list.toString(), HttpStatus.OK)
    }

    fun getCollection(id: Int): ResponseEntity<String> {
        val value = collectionRepo.findById(id)
        return if (value.isEmpty) ResponseEntity("The collection does not exist", HttpStatus.BAD_REQUEST)
        else ResponseEntity(value.get().toString(), HttpStatus.OK)
    }

    fun updateCollection(id: Int, updateCollection: CollectionEntity): ResponseEntity<String> {
        val oldValue = collectionRepo.findById(id)
        return if (oldValue.isEmpty) {
            ResponseEntity("The collection does not exist", HttpStatus.BAD_REQUEST)
        } else {
            updateCollection.id = oldValue.get().id
            collectionRepo.save(updateCollection)
            ResponseEntity("The collection is saved", HttpStatus.OK)
        }
    }

    fun deleteCollection(id: Int): ResponseEntity<String> {
        return if (collectionRepo.existsById(id)) {
            collectionRepo.deleteById(id)
            ResponseEntity("The collection was deleted", HttpStatus.ACCEPTED)
        }
        else ResponseEntity("The collection does not exist", HttpStatus.BAD_REQUEST)
    }
}