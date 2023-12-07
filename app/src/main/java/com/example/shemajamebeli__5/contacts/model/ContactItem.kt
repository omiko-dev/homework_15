package com.example.shemajamebeli__5.contacts.model

import com.squareup.moshi.Json

data class ContactItem(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "image") val image: String,
    @field:Json(name = "owner") val owner: String,
    @field:Json(name = "last_message") val lastMessage: String,
    @field:Json(name = "last_active") val lastActive: String,
    @field:Json(name = "unread_messages") val unreadMessages: Int,
    @field:Json(name = "is_typing") val isTyping: Boolean,
    @field:Json(name = "laste_message_type") val lastMessageType: String
)

enum class MessageType(val type: String) {
    TEXT("text"), VOICE("voice"), FILE("file")
}