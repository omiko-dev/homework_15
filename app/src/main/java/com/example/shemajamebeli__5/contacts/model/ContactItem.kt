package com.example.shemajamebeli__5.contacts.model

import com.squareup.moshi.Json

data class ContactItem(
    @Json(name = "id") val id: Int,
    @Json(name = "image") val image: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "last_message") val last_message: String,
    @Json(name = "last_active") val last_active: String,
    @Json(name = "unread_messages") val unread_messages: Int,
    @Json(name = "is_typing") val is_typing: Boolean,
    @Json(name = "laste_message_type") val laste_message_type: String
)

enum class MessageType(val type: String){
    TEXT("text"),
    VOICE("voice"),
    FILE("file")
}