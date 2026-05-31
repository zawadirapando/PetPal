package com.example.petpal.domain.model

import java.util.Date

data class User(
    val uid: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val role: Role,
    val createdAt: Date
)

enum class Role {
    OWNER,
    SITTER,
    UNKNOWN
}