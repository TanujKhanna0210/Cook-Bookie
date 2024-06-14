package com.example.cookbookie.data.repository

import com.example.cookbookie.data.local.RecipeDao
import com.example.cookbookie.domain.model.Recipe
import com.example.cookbookie.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val recipeDao: RecipeDao
) : RecipeRepository {
    override fun getAllRecipes(): Flow<List<Recipe>> {
        return recipeDao.getAllRecipes()
    }

    override fun getRecipesByCategory(category: String): Flow<List<Recipe>> {
        return recipeDao.getRecipesByCategory(category)
    }

    override suspend fun getRecipe(recipeId: Int): Recipe {
        return recipeDao.getRecipe(recipeId)
    }

    override suspend fun upsertRecipe(recipe: Recipe) {
        recipeDao.upsertRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }

    override fun searchRecipes(query: String): Flow<List<Recipe>> {
        return recipeDao.searchRecipes(query)

    }

    override fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return recipeDao.getFavoriteRecipes()
    }

    override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        recipeDao.updateFavoriteStatus(id, isFavorite)
    }

}