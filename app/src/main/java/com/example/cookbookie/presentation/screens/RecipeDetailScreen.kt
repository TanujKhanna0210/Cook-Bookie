package com.example.cookbookie.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbookie.RecipeViewModel
import com.example.cookbookie.presentation.components.InsertRecipeComponent
import com.example.cookbookie.presentation.components.UpdateRecipeComponent

@Composable
fun RecipeDetailScreen(
    recipeId: Int = -1,
    onBackClick: () -> Unit,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    if (recipeId != -1) {

        UpdateRecipeComponent(viewModel, recipeId, onBackClick)

    } else {

        InsertRecipeComponent(onBackClick, viewModel)

    }
}