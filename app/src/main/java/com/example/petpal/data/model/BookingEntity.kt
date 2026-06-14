package com.example.petpal.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class BookingEntity(
    @DocumentId val bookingId: String = "",
    val ownerId: String = "",
    val sitterId: String = "",
    val petIds: List<String> = emptyList(),
    val startTime: Timestamp = Timestamp.now(),
    val endTime: Timestamp = Timestamp.now(),
    val totalPrice: String = "",
    val status: String = "PENDING"
)