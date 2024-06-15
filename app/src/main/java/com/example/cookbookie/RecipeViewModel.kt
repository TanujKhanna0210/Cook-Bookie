package com.example.cookbookie

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbookie.data.local.RecipeTypeConverter
import com.example.cookbookie.domain.model.Recipe
import com.example.cookbookie.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    var recipe by mutableStateOf(
        Recipe(title = "", category = "", ingredients = "", instructions = "", rating = 0, id = -1)
    )
        private set

    private val _allRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val allRecipes: StateFlow<List<Recipe>> = _allRecipes.asStateFlow()

    private val _appetizers = MutableStateFlow<List<Recipe>>(emptyList())
    val appetizers: StateFlow<List<Recipe>> = _appetizers.asStateFlow()

    private val _mainCourse = MutableStateFlow<List<Recipe>>(emptyList())
    val mainCourse: StateFlow<List<Recipe>> = _mainCourse.asStateFlow()

    private val _sideDishes = MutableStateFlow<List<Recipe>>(emptyList())
    val sideDishes: StateFlow<List<Recipe>> = _sideDishes.asStateFlow()

    private val _desserts = MutableStateFlow<List<Recipe>>(emptyList())
    val desserts: StateFlow<List<Recipe>> = _desserts.asStateFlow()

    private val _snacks = MutableStateFlow<List<Recipe>>(emptyList())
    val snacks: StateFlow<List<Recipe>> = _snacks.asStateFlow()

    private val _beverages = MutableStateFlow<List<Recipe>>(emptyList())
    val beverages: StateFlow<List<Recipe>> = _beverages.asStateFlow()

    private val _searchResults = MutableStateFlow<List<Recipe>>(emptyList())
    val searchResults: StateFlow<List<Recipe>> = _searchResults

    val favoriteRecipes: Flow<List<Recipe>> = repository.getFavoriteRecipes()

    init {
        loadAllRecipes()
    }

    private fun loadAllRecipes() {
        viewModelScope.launch {
            repository.getAllRecipes().collect { recipes ->
                _allRecipes.value = recipes
                _appetizers.value = recipes.filter { it.category == "Appetizer" }
                _mainCourse.value = recipes.filter { it.category == "Main Course" }
                _sideDishes.value = recipes.filter { it.category == "Side Dish" }
                _desserts.value = recipes.filter { it.category == "Dessert" }
                _snacks.value = recipes.filter { it.category == "Snacks" }
                _beverages.value = recipes.filter { it.category == "Beverage" }
            }
        }
    }

    fun toggleFavoriteStatus(recipe: Recipe) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(recipe.id, !recipe.favorite)
        }
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            repository.searchRecipes(query).collect {
                _searchResults.value = it
            }
        }
    }

    fun getRecipe(recipeId: Int) = viewModelScope.launch {
        recipe = repository.getRecipe(recipeId)
    }

    fun upsertRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.upsertRecipe(recipe)
        loadAllRecipes()
    }

    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.deleteRecipe(recipe)
        loadAllRecipes()
    }


    fun updateTitle(title: String) {
        recipe = recipe.copy(
            title = title
        )
    }

    fun updateCategory(category: String) {
        recipe = recipe.copy(
            category = category
        )
    }

    fun updateIngredients(ingredients: String) {
        recipe = recipe.copy(
            ingredients = ingredients
        )
    }

    fun updateInstructions(instructions: String) {
        recipe = recipe.copy(
            instructions = instructions
        )
    }

    fun updateRating(rating: Int) {
        recipe = recipe.copy(
            rating = rating
        )
    }

    fun fromBitmap(bitmap: Bitmap): ByteArray {
        return RecipeTypeConverter().fromBitmap(bitmap)
    }

    fun toBitmap(byteArray: ByteArray): Bitmap {
        return RecipeTypeConverter().toBitmap(byteArray)
    }
}