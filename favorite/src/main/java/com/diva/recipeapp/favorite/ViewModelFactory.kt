package com.diva.recipeapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diva.recipeapp.core.domain.usecase.RecipeUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val recipeUseCase: RecipeUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(recipeUseCase) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}