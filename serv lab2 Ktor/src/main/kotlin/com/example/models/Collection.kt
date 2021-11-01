package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Collection(

    val id: Int? = null,
    val name: String,
    val type: String,
    val description: String,
    val cost: Int,
    val userId: Int

)