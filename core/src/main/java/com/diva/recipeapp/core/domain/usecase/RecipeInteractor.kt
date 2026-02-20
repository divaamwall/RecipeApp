package com.diva.recipeapp.core.domain.usecase

import com.diva.recipeapp.core.domain.model.Recipe
import com.diva.recipeapp.core.domain.repository.IRecipeRepository
import javax.inject.Inject

class RecipeInteractor @Inject constructor(
    private val recipeRepository : IRecipeRepository
) : RecipeUseCase {
    override fun getAllRecipe() = recipeRepository.getAllRecipe()

    override fun getFavoriteRecipe() = recipeRepository.getFavoriteRecipe()
    override fun setFavoriteRecipe(
        recipe: Recipe,
        state: Boolean
    ) = recipeRepository.setFavoriteRecipe(recipe, state)


}