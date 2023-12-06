package com.example.sitroutedriverapp.models

import com.google.gson.annotations.SerializedName

data class Message (
    @SerializedName("idMessage")
    val idMessage: Int? = null,
    @SerializedName("value")
    val value: String,
    @SerializedName("idSender")
    val idSender: Int,
    @SerializedName("idRecipient")
    val idRecipient: Int? = null,
    @SerializedName("time")
    val time: String,
    @SerializedName("idRecipientNavigation")
    val idRecipientNavigation: User? = null,
    @SerializedName("idSenderNavigation")
    val idSenderNavigation: User? = null
)