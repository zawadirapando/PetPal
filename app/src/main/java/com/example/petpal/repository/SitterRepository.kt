package com.example.petpal.repository

import com.example.petpal.domain.model.Sitter

interface SitterRepository{
    suspend fun getNearbySitters(): Result<List<Sitter>>
}