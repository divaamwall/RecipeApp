package com.diva.recipeapp.core.data

import com.diva.recipeapp.core.data.source.local.LocalDataSource
import com.diva.recipeapp.core.data.source.remote.RemoteDataSource
import com.diva.recipeapp.core.data.source.remote.network.ApiResponse
import com.diva.recipeapp.core.data.source.remote.response.RecipeResponse
import com.diva.recipeapp.core.domain.model.Recipe
import com.diva.recipeapp.core.domain.repository.IRecipeRepository
import com.diva.recipeapp.core.utils.AppExecutors
import com.diva.recipeapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRecipeRepository {
    override fun getAllRecipe(): Flow<Resource<List<Recipe>>> =
        object : NetworkBoundResource<List<Recipe>, List<RecipeResponse>>(){
            override fun loadFromDB(): Flow<List<Recipe>> {
                return localDataSource.getAllRecipe().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Recipe>?): Boolean =
                data == null || data.isEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<RecipeResponse>>> =
                remoteDataSource.getAllRecipe()


            override suspend fun saveCallResult(data: List<RecipeResponse>) {
                val recipeList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertRecipe(recipeList)
            }
        }.asFlow()

    override fun getFavoriteRecipe(): Flow<List<Recipe>> {
        return localDataSource.getFavoriteRecipe().map { DataMapper.mapEntitiesToDomain(it) }

    }

    override fun setFavoriteRecipe(
        recipe: Recipe,
        state: Boolean
    ) {
        val recipeEntity = DataMapper.mapDomainToEntity(recipe)
        appExecutors.diskIO().execute { localDataSource.setFavoriteRecipe(recipeEntity, state) }
    }
}