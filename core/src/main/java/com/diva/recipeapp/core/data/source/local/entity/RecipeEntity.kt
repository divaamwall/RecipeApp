package com.diva.recipeapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "recipes")
data class RecipeEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "ingredients")
    val ingredients: String,

    @ColumnInfo(name = "instructions")
    val instructions: String,

    @ColumnInfo(name = "prep_time_minutes")
    val prepTimeMinutes: Int,

    @ColumnInfo(name = "cook_time_minutes")
    val cookTimeMinutes: Int,

    @ColumnInfo(name = "servings")
    val servings: Int,

    @ColumnInfo(name = "difficulty")
    val difficulty: String,

    @ColumnInfo(name = "cuisine")
    val cuisine: String,

    @ColumnInfo(name = "calories_per_serving")
    val caloriesPerServing: Int,

    @ColumnInfo(name = "tags")
    val tags: String,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "review_count")
    val reviewCount: Int,

    @ColumnInfo(name = "meal_type")
    val mealType: String,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false

) : Parcelable