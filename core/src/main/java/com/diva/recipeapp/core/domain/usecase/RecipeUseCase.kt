package com.diva.recipeapp.core.domain.usecase

import com.diva.recipeapp.core.data.Resource
import com.diva.recipeapp.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeUseCase {

    fun getAllRecipe(): Flow<Resource<List<Recipe>>>

    fun getFavoriteRecipe(): Flow<List<Recipe>>

    fun setFavoriteRecipe(recipe: Recipe, state: Boolean)
}