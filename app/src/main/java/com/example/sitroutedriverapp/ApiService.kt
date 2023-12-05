package com.example.sitroutedriverapp

import com.example.sitroutedriverapp.models.Message
import com.example.sitroutedriverapp.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyApiService {
    @GET("/drivers/{login}/{password}")
    fun getCurrentUser(@Path("login") login: String, @Path("password") password:String): Call<User>

    @GET("/chat/{idDriver}")
    fun getMessages(@Path("idDriver") idDriver: Int): Call<List<Message>>
}