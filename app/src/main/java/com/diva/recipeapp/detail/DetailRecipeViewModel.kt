package com.diva.recipeapp.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.diva.recipeapp.core.domain.model.Recipe
import com.diva.recipeapp.core.domain.usecase.RecipeUseCase

@HiltViewModel
class DetailRecipeViewModel @Inject constructor(
    private val recipeUseCase: RecipeUseCase
) : ViewModel() {
    fun setFavoriteRecipe(recipe: Recipe, newStatus: Boolean) {
        recipeUseCase.setFavoriteRecipe(recipe, newStatus)
    }
}