package com.example.petpal.repository

import com.example.petpal.domain.model.Pet

interface PetRepository {
    suspend fun getPetById(petId: String): Result<Pet>
}