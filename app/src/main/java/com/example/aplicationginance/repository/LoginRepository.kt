package com.example.aplicationginance.repository

import com.example.aplicationginance.model.LoginRequest
import com.example.aplicationginance.model.LoginResponse
import com.example.aplicationginance.network.LoginApi


class LoginRepository(private val api : LoginApi) {

    suspend fun login(username : String,password : String): Result<LoginResponse>
    {
        return try {
            val response = api.login(LoginRequest(username,password))
            Result.success(response)
        }
        catch (e:Exception){
            Result.failure(e)
        }
    }
}