package com.pmdm.examen_extraordinaria.data_remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dummyjson.com/"

    val apiService : RecetasApiService by lazy{

        val gson = GsonBuilder()
            .setLenient() // Permite JSON más flexible
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RecetasApiService::class.java)
    }
}