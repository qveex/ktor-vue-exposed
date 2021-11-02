package com.example.servlab2.controllers

import com.example.servlab2.entities.CollectionEntity
import com.example.servlab2.services.CollectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@RequestMapping("/api/collections")
class CollectionController {

    @Autowired
    private lateinit var collectionService: CollectionService

    @GetMapping
    fun getCollections() = collectionService.getAllCollections()

    @GetMapping("/{id}")
    fun getCollection(@PathVariable id: Int) = collectionService.getCollection(id)

    @PostMapping
    fun createCollection(@RequestBody collection: CollectionEntity) = collectionService.createCollection(collection)

    @PutMapping("/{id}")
    fun updateCollection(@PathVariable id: Int,
                         @RequestBody collection: CollectionEntity)
    = collectionService.updateCollection(id, collection)

    @DeleteMapping("/{id}")
    fun deleteCollection(@PathVariable id: Int) = collectionService.deleteCollection(id)

    @GetMapping("/tea")
    fun doYouWantSomeTea() = ResponseEntity("Do you want some tea?", HttpStatus.I_AM_A_TEAPOT)
}