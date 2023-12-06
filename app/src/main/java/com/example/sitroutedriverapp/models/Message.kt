package com.example.sitroutedriverapp.models

import com.google.gson.annotations.SerializedName

data class Message (
    val idMessage: Int? = null,
    val value: String,
    val idSender: Int,
    val idRecipient: Int? = null,
    val time: String? = null,
    val idRecipientNavigation: User? = null,
    val idSenderNavigation: User? = null
)