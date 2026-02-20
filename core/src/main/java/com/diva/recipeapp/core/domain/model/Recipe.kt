package com.diva.recipeapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: String,
    val instructions: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val servings: Int,
    val difficulty: String,
    val cuisine: String,
    val caloriesPerServing: Int,
    val tags: String,
    val userId: Int,
    val image: String,
    val rating: Double,
    val reviewCount: Int,
    val mealType: String,
    var isFavorite: Boolean = false
) : Parcelable