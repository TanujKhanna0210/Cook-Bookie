package com.example.cookbookie.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbookie.RecipeViewModel
import com.example.cookbookie.presentation.components.InsertRecipeComponent

@Composable
fun RecipeDetailScreen(
    itemId: Int = -1,
    onBackClick: () -> Unit,
    viewModel: RecipeViewModel = hiltViewModel()
) {

    if (itemId != -1) {

//        EditRecipeComponent(viewModel, itemId, onBackClick)

    } else {

        InsertRecipeComponent(onBackClick, viewModel)

    }
}