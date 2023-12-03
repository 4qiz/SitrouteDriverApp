package com.example.sitroutedriverapp.models

data class User (
    var idUser: Int = 0,
    var firstName: String = "",
    var patronymic: String? = null,
    var secondName: String = "",
    var password: ByteArray = byteArrayOf(),
    var login: String = "",
    var driver: Driver? = null
)
