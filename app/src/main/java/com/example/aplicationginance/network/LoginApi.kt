package com.example.aplicationginance.network

import com.example.aplicationginance.model.LoginRequest
import com.example.aplicationginance.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("usuario/login")
    suspend fun login(@Body request: LoginRequest):LoginResponse
}