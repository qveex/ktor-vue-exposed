package com.example.servlab2.repository

import com.example.servlab2.entities.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepo: CrudRepository<UserEntity, Int> {

    fun findByLogin(login: String): UserEntity
}