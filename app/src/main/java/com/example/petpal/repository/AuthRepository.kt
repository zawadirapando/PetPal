package com.example.petpal.repository

import android.provider.ContactsContract.CommonDataKinds.Email

interface AuthRepository {
    suspend fun logIn(email: String, password: String): Result<Unit>
    suspend fun signUp(email: String, password: String): Result<Unit>
    suspend fun resetPassword(email: String): Result<Unit>
    fun isUserAuthenticated(): Boolean
}

