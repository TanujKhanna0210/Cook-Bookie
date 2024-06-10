package com.example.cookbookie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    // TODO : Other queries based on categories
}