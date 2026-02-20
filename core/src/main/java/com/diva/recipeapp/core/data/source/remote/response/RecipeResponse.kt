package com.diva.recipeapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(

	@field:SerializedName("cookTimeMinutes")
	val cookTimeMinutes: Int,

	@field:SerializedName("instructions")
	val instructions: List<String>,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("prepTimeMinutes")
	val prepTimeMinutes: Int,

	@field:SerializedName("caloriesPerServing")
	val caloriesPerServing: Int,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("mealType")
	val mealType: List<String>,

	@field:SerializedName("cuisine")
	val cuisine: String,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("tags")
	val tags: List<String>,

	@field:SerializedName("difficulty")
	val difficulty: String,

	@field:SerializedName("servings")
	val servings: Int,

	@field:SerializedName("reviewCount")
	val reviewCount: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("ingredients")
	val ingredients: List<String>,

	@field:SerializedName("id")
	val id: Int
)
