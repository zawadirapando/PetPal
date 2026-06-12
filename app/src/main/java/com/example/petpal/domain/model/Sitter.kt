package com.example.petpal.domain.model

import com.google.firebase.firestore.DocumentId

data class Sitter(
    val id: String,
    val firstName: String,
    val lastName: String,
    val distanceKm: Double,
    val price: String,
    val isVerified: Boolean,
    val acceptedPets: String,
    val matchScore: Int?
)