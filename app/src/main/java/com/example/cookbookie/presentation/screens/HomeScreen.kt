package com.example.cookbookie.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbookie.RecipeViewModel
import com.example.cookbookie.presentation.components.RecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToDetailsScreen: (itemId: Int) -> Unit,
    onBackCLick: () -> Unit,
    viewModel: RecipeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val recipes by viewModel.getAllRecipes().collectAsState(
        initial = emptyList()
    )

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxSize()
    ) {

        Scaffold(
//            floatingActionButton = {
//                FloatingActionButton(onClick = { navigateToDetailsScreen(-1) }) {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = "Add recipe"
//                    )
//                }
//            }
        ) {
            val topPadding = it.calculateTopPadding()
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = topPadding),
                contentPadding = PaddingValues(6.dp)
            ) {
                items(count = recipes.size) { index ->
                    val recipe = recipes[index]
                    RecipeCard(recipe = recipe,
                        navigateToDetailsScreen = {
                            navigateToDetailsScreen(recipe.id)
                        },
                        onDelete = {
                            viewModel.deleteRecipe(recipe)
                        })
                }
            }
        }

    }
}