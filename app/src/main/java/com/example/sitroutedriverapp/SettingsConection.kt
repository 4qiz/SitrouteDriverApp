package com.example.sitroutedriverapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun settingsConnection(): MyApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://Dimaso.bsite.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MyApiService::class.java)
}