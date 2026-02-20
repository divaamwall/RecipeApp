package com.diva.recipeapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.diva.recipeapp.core.data.source.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE is_favorite = 1")
    fun getFavoriteRecipes(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeEntity>)

    @Update
    fun updateFavoriteRecipe(recipe: RecipeEntity)

}