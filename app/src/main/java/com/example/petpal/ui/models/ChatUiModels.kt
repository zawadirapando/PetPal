package com.example.petpal.ui.models

//blueprint for one row
data class ChatThreadUiModels (
    val threadId: String,
    val sitterName: String,
    val initials: String,
    val lastMessage: String,
    val timestamp: String,
    val unreadCount: Int = 0,
    val isOnline: Boolean = false
)

enum class MessageType {
    SENDER,
    RECEIVER
}

data class ChatMessageUiModel (
    val threadId: String,
    val text: String,
    val timestamp: String,
    val type: MessageType
)