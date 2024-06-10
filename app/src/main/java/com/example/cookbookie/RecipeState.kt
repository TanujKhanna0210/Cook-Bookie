package com.example.cookbookie

data class RecipeState(
    val recipes: List<Recipe> = emptyList(),
    val title: String = "",
    val category: String = "",
    val ingredients: String = "",
    val instructions: String = "",
//    val image: Bitmap? = null,
    val isAddingRecipe: Boolean = false
)
