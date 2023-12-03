package com.example.sitroutedriverapp.models

import java.util.Date

data class Driver (
    var idDriver: Int = 0,
    var startTime: Date? = null,
    var endTime: Date? = null
)