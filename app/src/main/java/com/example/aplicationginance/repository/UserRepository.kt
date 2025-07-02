package com.example.aplicationginance.repository

import com.example.aplicationginance.model.ListUser
import com.example.aplicationginance.network.UserApi
import com.example.aplicationginance.model.Usuario

class UserRepository(private val api : UserApi) {

    suspend fun getUsers():Result<ListUser> {
        return try {
            Result.success(api.getAll())
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }

}