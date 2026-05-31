package com.example.petpal.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class UserEntity (
    @DocumentId val uid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val role: String = "OWNER",
    val createdAt: Timestamp = Timestamp.now()
)