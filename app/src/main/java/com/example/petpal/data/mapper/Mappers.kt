package com.example.petpal.data.mapper

import com.example.petpal.data.model.*
import com.example.petpal.domain.model.*

//maps UserEntity (Firebase) to User(UI)
fun UserEntity.toDomain(): User {
    return User(
        uid = this.uid,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
        role = try {
            Role.valueOf(this.role.uppercase())
        } catch (e: Exception) {
            Role.UNKNOWN
        },
        createdAt = this.createdAt.toDate()
    )
}

//maps PetEntity (Firebase) to Pet(UI)
fun PetEntity.toDomain(): Pet {
    return Pet(
        petId = this.petId,
        ownerId = this.ownerId,
        name = this.name,
        type = this.type,
        breed = this.breed,
        age = this.age,
        medicalNotes = this.medicalNotes
    )
}

//maps BookingEntity (Firebase) to Booking(UI)
fun BookingEntity.toDomain(): Booking {
    return Booking(
        bookingId = this.bookingId,
        ownerId = this.ownerId,
        sitterId = this.sitterId,
        petIds = this.petIds,
        startTime = this.startTime.toDate(),
        endTime = this.endTime.toDate(),
        status = try {
            BookingStatus.valueOf(this.status.uppercase())
        } catch (e: Exception) {
            BookingStatus.UNKNOWN
        }
    )
}

fun SitterEntity.toDomain(): Sitter {
    return Sitter(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        distanceKm = this.distanceKm,
        price = this.price,
        isVerified = this.isVerified,
        acceptedPets = this.acceptedPets,
        matchScore = this.matchScore,
    )
}
