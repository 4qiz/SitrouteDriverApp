package com.example.sitroutedriverapp.models

data class Bus(
    val idBus: Int,
    val isBroken: Boolean,
    val number: String,
    val location: String?,
    val idRoute: Int?,
    val charge: Int?,
    val averageChargeDrop: Int?,
    val capacity: Int,
    val drivers: List<Driver> = emptyList()
)