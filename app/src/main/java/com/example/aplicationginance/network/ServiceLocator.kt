package com.example.aplicationginance.network


import com.example.aplicationginance.repository.LoginRepository
import com.example.aplicationginance.repository.UserRepository

object ServiceLocator {
    private val userApi = RetrofitInstance.userApi
    private val loginApi = RetrofitInstance.loginApi


    val loginRepository by lazy {
        LoginRepository(loginApi)
    }

    val userRepository by lazy {
        UserRepository(userApi)
    }


}