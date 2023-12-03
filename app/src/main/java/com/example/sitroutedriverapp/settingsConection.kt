package com.example.sitroutedriverapp

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun settingsConnection(): MyApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:5038")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(MyApiService::class.java)
}