package com.diva.recipeapp.core.data.source.remote.network

import com.diva.recipeapp.core.data.source.remote.response.ListRecipeResponse
import retrofit2.http.GET

interface ApiService {

    @GET("recipes")
    suspend fun getList(): ListRecipeResponse
}