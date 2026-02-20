package com.diva.recipeapp.core.data.source.remote

import android.util.Log
import com.diva.recipeapp.core.data.source.remote.network.ApiResponse
import com.diva.recipeapp.core.data.source.remote.network.ApiService
import com.diva.recipeapp.core.data.source.remote.response.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllRecipe(): Flow<ApiResponse<List<RecipeResponse>>>{
        return flow {
            try {
                val response = apiService.getList()
                val dataArray = response.recipes
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.recipes))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}