package com.diva.recipeapp.core.utils

import com.diva.recipeapp.core.data.source.local.entity.RecipeEntity
import com.diva.recipeapp.core.data.source.remote.response.RecipeResponse
import com.diva.recipeapp.core.domain.model.Recipe
import kotlin.collections.map

object DataMapper {

    fun mapResponsesToEntities(input: List<RecipeResponse>): List<RecipeEntity> {
        val recipeList = ArrayList<RecipeEntity>()


        input.map {
            val recipe = RecipeEntity(
                id = it.id,
                name = it.name,
                ingredients = it.ingredients.joinToString("\n") { ingredient -> "• $ingredient" },
                instructions = it.instructions.mapIndexed { index, step ->
                    "${index + 1}. $step"
                }.joinToString("\n"),
                prepTimeMinutes = it.prepTimeMinutes,
                cookTimeMinutes = it.cookTimeMinutes,
                servings = it.servings,
                difficulty = it.difficulty,
                cuisine = it.cuisine,
                caloriesPerServing = it.caloriesPerServing,
                tags = it.tags.joinToString(","),
                userId = it.userId,
                image = it.image,
                rating = it.rating,
                reviewCount = it.reviewCount,
                mealType = it.mealType.joinToString(","),
                isFavorite = false
            )
            recipeList.add(recipe)
        }

        return recipeList
    }

    fun mapEntitiesToDomain(input: List<RecipeEntity>): List<Recipe> =
        input.map {
            Recipe(
                id = it.id,
                name = it.name,
                ingredients = it.ingredients,
                instructions = it.instructions,
                prepTimeMinutes = it.prepTimeMinutes,
                cookTimeMinutes = it.cookTimeMinutes,
                servings = it.servings,
                difficulty = it.difficulty,
                cuisine = it.cuisine,
                caloriesPerServing = it.caloriesPerServing,
                tags = it.tags,
                userId = it.userId,
                image = it.image,
                rating = it.rating,
                reviewCount = it.reviewCount,
                mealType = it.mealType,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Recipe) = RecipeEntity(
        id = input.id,
        name = input.name,
        ingredients = input.ingredients,
        instructions = input.instructions,
        prepTimeMinutes = input.prepTimeMinutes,
        cookTimeMinutes = input.cookTimeMinutes,
        servings = input.servings,
        difficulty = input.difficulty,
        cuisine = input.cuisine,
        caloriesPerServing = input.caloriesPerServing,
        tags = input.tags,
        userId = input.userId,
        image = input.image,
        rating = input.rating,
        reviewCount = input.reviewCount,
        mealType = input.mealType,
        isFavorite = input.isFavorite
    )
}