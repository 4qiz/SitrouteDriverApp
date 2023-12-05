package com.example.sitroutedriverapp.models

import java.time.LocalDateTime

data class Message (
    val idMessage: Int,
    val value: String,
    val idSender: Int,
    val idRecipient: Int?,
    val time: LocalDateTime,
    val idRecipientNavigation: User?,
    val idSenderNavigation: User
)