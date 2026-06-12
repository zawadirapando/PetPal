package com.example.petpal.repository

import com.example.petpal.data.mapper.toDomain
import com.example.petpal.data.model.BookingEntity
import com.example.petpal.domain.model.Booking
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BookingRepositoryImpl (
    private val firestore: FirebaseFirestore
): BookingRepository{
    override suspend fun getUpcomingBookingForUser(userId: String): Result<List<Booking>> {
        return try {
            val snapshot = firestore.collection("bookings")
                .whereEqualTo("onwerId", userId)
                .get()
                .await()
            val bookings = snapshot.toObjects(BookingEntity::class.java).map { it.toDomain() }
            Result.success(bookings)
        } catch (e:Exception) {
            Result.failure(e)
        }
    }
}