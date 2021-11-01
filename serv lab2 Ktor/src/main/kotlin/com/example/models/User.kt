package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: Int? = null,
    val login: String,
    val hash: String,
)