package com.example.petpal.data.model

import com.google.firebase.firestore.DocumentId

data class SitterEntity (
    @DocumentId val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val distanceKm: Double = 0.0,
    val price: String = "",
    val isVerified: Boolean = false,
    val acceptedPets: String = "All pets",
    val matchScore: Int? = null
)