package com.example.cookbookie.domain.repository

import com.example.cookbookie.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getAllRecipes(): Flow<List<Recipe>>

    fun getRecipesByCategory(category: String): Flow<List<Recipe>>

    suspend fun getRecipe(recipeId: Int): Recipe

    suspend fun upsertRecipe(recipe: Recipe)

    suspend fun deleteRecipe(recipe: Recipe)

}