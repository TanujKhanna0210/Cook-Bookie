package com.example.cookbookie.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.cookbookie.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Upsert
    suspend fun upsertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE id=:recipeId")
    suspend fun getRecipe(recipeId: Int): Recipe

    @Query("SELECT * FROM recipe WHERE category=:category")
    fun getRecipesByCategory(category: String = "All"): Flow<List<Recipe>>
}