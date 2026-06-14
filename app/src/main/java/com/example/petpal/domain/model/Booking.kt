package com.example.petpal.domain.model

import java.util.Date

data class Booking(
    val bookingId: String,
    val ownerId: String,
    val sitterId: String,
    val petIds: List<String>,
    val startTime: Date,
    val endTime: Date,
    val totalPrice: String,
    val status: BookingStatus
)

enum class BookingStatus {
    PENDING,
    CONFIRMED,
    COMPLETED,
    CANCELLED,
    UNKNOWN
}