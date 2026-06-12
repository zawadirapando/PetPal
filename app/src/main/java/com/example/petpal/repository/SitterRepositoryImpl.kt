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
}