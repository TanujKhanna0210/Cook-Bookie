package com.example.cookbookie.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbookie.RecipeViewModel
import com.example.cookbookie.presentation.components.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    navigateToDetailsScreen: (itemId: Int) -> Unit,
    viewModel: RecipeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val favoriteRecipes by viewModel.favoriteRecipes.collectAsState(initial = emptyList())

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxSize()
    ) {
        Scaffold { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding()

            Column(
                modifier = Modifier.padding(top = topPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Top Picks!",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                )

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    items(count = favoriteRecipes.size) { index ->
                        val recipe = favoriteRecipes[index]
                        RecipeCard(
                            recipe = recipe,
                            navigateToDetailsScreen = { navigateToDetailsScreen(recipe.id) },
                            showDeleteIcon = false,
                            onDelete = { viewModel.deleteRecipe(recipe) },
                            onFavoriteClick = { viewModel.toggleFavoriteStatus(recipe) }
                        )
                    }
                }
            }
        }
    }
}
