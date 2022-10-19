package com.example.my_app_for_test.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object helper {


    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}