package com.example.petpal.domain.model

import java.util.Date

data class Pet(
    val petId: String,
    val ownerId: String,
    val name: String,
    val type: String,
    val breed: String,
    val age: Int,
    val medicalNotes: String
)