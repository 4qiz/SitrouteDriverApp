package com.example.sitroutedriverapp

import com.example.sitroutedriverapp.models.Message
import com.example.sitroutedriverapp.models.Route
import com.example.sitroutedriverapp.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/drivers/{login}/{password}")
    fun getCurrentUser(@Path("login") login: String, @Path("password") password:String): Call<User>

    @GET("/chat/{idDriver}")
    fun getMessages(@Path("idDriver") idDriver: Int): Call<List<Message>>

    @GET("/routesByBusStation/{idDriver}")
    fun getRoutes(@Path("idDriver") idDriver: Int): Call<List<Route>>

    @POST("/message")
    fun sendMessage(@Body message: Message): Call<Unit>
}