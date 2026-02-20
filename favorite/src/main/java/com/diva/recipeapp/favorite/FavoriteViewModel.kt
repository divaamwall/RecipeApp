package com.diva.recipeapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.diva.recipeapp.core.domain.usecase.RecipeUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    recipeUseCase: RecipeUseCase
) : ViewModel() {
    val favoriteRecipe = recipeUseCase.getFavoriteRecipe().asLiveData()
}