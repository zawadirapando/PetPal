package com.example.petpal.repository

import com.example.petpal.data.mapper.toDomain
import com.example.petpal.data.model.SitterEntity
import com.example.petpal.domain.model.Sitter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SitterRepositoryImpl (
    private val firestore: FirebaseFirestore
): SitterRepository {
    override suspend fun getNearbySitters(): Result<List<Sitter>> {
        return try {
            val snapshot = firestore.collection("sitters")
                .get()
                .await()
            val sitters = snapshot.toObjects(SitterEntity::class.java).map { it.toDomain() }
            Result.success(sitters)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getSitterById(sitterId: String): Result<Sitter> {
        return try {
            val document = firestore.collection("sitters")
                .document(sitterId)
                .get()
                .await()

            if (document.exists()){
                val entity = document.toObject(SitterEntity::class.java)
                if (entity != null){
                    Result.success(entity.toDomain())
                } else {
                    Result.failure(Exception("Fail to parse Sitter"))
                }
            } else {
                Result.failure(Exception("Sitter not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}