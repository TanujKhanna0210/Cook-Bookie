package com.example.cookbookie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbookie.domain.model.Recipe
import com.example.cookbookie.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    var recipe by mutableStateOf(
        Recipe("", "", "", "", -1)
    )
        private set

    fun getAllRecipes() = repository.getAllRecipes()

    fun getRecipe(recipeId: Int) = viewModelScope.launch {
        recipe = repository.getRecipe(recipeId)
    }

    fun upsertRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.upsertRecipe(recipe)
    }

    fun deleteRecipe(recipe: Recipe) = viewModelScope.launch {
        repository.deleteRecipe(recipe)
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

//    fun updateImage(image: Bitmap) {
//        recipe = recipe.copy(
//            image = image
//        )
//    }

//    fun getBitmapFromDrawable(context: Context, drawableId: Int): Bitmap {
//        val drawable = ContextCompat.getDrawable(context, drawableId)
//        return (drawable as BitmapDrawable).bitmap
//    }
}