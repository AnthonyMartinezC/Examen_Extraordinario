package com.pmdm.examen_extraordinaria.data_remote

import com.pmdm.examen_extraordinaria.model.Recetas
import retrofit2.http.GET
import retrofit2.http.Query

interface RecetasApiService {
    @GET("recipes")
    suspend fun getAllRecipes(): Recetas
}