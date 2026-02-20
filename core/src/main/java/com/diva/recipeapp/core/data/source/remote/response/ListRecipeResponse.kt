package com.diva.recipeapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListRecipeResponse(
    @field:SerializedName("recipes")
    val recipes: List<RecipeResponse>
)
