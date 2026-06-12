package com.example.petpal.repository

import com.example.petpal.domain.model.Booking

interface BookingRepository{
    suspend fun getUpcomingBookingForUser(userId: String): Result<List<Booking>>
}