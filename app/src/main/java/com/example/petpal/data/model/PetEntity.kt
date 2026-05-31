package com.example.petpal.data.model

import com.google.firebase.firestore.DocumentId

data class PetEntity(
    @DocumentId val petId: String = "",
    val ownerId: String = "",
    val name: String = "",
    val type: String = "",
    val breed: String = "",
    val age: Int = 0,
    val medicalNotes: String = ""
)