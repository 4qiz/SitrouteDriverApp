package com.example.sitroutedriverapp.models

data class Message (
    val idMessage: Int,
    val value: String,
    val idSender: Int,
    val idRecipient: Int?,
    val time: String,
    val idRecipientNavigation: User?,
    val idSenderNavigation: User?
)