package com.example.petpal.repository

import com.example.petpal.data.mapper.toDomain
import com.example.petpal.data.model.PetEntity
import com.example.petpal.domain.model.Pet
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class PetRepositoryImpl(
    private val firestore: FirebaseFirestore
) : PetRepository {
    override suspend fun getPetById(petId: String): Result<Pet> {
        return try {
            val document = firestore.collection("pets")
                .document(petId)
                .get()
                .await()

            if (document.exists()) {
                val petEntity = document.toObject(PetEntity::class.java)
                if (petEntity != null){
                    Result.success(petEntity.toDomain())
                } else {
                    Result.failure(Exception("Failed to parse pet data"))
                }
            } else {
                Result.failure(Exception("Pet document not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}