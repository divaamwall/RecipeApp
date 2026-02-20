package com.diva.recipeapp.core.data.source.local

import com.diva.recipeapp.core.data.source.local.entity.RecipeEntity
import com.diva.recipeapp.core.data.source.local.room.RecipeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor( private val recipeDao: RecipeDao) {
    fun getAllRecipe(): Flow<List<RecipeEntity>> = recipeDao.getAllRecipes()

    fun getFavoriteRecipe(): Flow<List<RecipeEntity>> = recipeDao.getFavoriteRecipes()

    suspend fun insertRecipe(recipeList: List<RecipeEntity>) = recipeDao.insertRecipes(recipeList)


    fun setFavoriteRecipe(recipe: RecipeEntity, newState: Boolean){
        recipe.isFavorite = newState
        recipeDao.updateFavoriteRecipe(recipe)
    }
}