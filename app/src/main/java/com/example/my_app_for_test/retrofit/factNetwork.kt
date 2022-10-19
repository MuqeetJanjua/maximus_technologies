package com.example.my_app_for_test.retrofit

import com.example.my_app_for_test.model.Fact
import retrofit2.Call
import retrofit2.http.GET

const val BASE_URL = "https://catfact.ninja/"

interface factNetwork {

    @GET("fact")
    fun getfact(): Call<Fact>

}