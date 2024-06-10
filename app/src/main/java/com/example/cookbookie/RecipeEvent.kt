package com.example.cookbookie

sealed interface RecipeEvent {
    object SaveRecipe : RecipeEvent
    data class DeleteRecipe(val recipe: Recipe) : RecipeEvent

    data class SetTitle(val title: String) : RecipeEvent
    data class SetCategory(val category: String) : RecipeEvent
    data class SetIngredients(val ingredients: String) : RecipeEvent
    data class SetInstructions(val instructions: String) : RecipeEvent
//    data class SetImage(val image: Bitmap?) : RecipeEvent

    object ShowDialog : RecipeEvent
    object HideDialog : RecipeEvent


    // TODO: Other events based on categories
}