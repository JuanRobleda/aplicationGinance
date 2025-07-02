package com.example.aplicationginance.network


import com.example.aplicationginance.model.ListUser
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("usuario/all")
    suspend fun getAll(): ListUser
}