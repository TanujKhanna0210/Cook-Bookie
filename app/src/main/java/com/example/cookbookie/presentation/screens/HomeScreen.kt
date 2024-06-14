package com.example.cookbookie.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cookbookie.RecipeViewModel
import com.example.cookbookie.presentation.components.RecipeCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToDetailsScreen: (itemId: Int) -> Unit,
    onBackCLick: () -> Unit,
    viewModel: RecipeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val categories =
        listOf("All", "Appetizers", "Main Course", "Side Dishes", "Desserts", "Snacks", "Beverages")

    val recipes by when (selectedTabIndex) {
        1 -> viewModel.appetizers.collectAsState()
        2 -> viewModel.mainCourse.collectAsState()
        3 -> viewModel.sideDishes.collectAsState()
        4 -> viewModel.desserts.collectAsState()
        5 -> viewModel.snacks.collectAsState()
        6 -> viewModel.beverages.collectAsState()
        else -> viewModel.allRecipes.collectAsState()
    }

    var searchQuery by remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxSize()
    ) {
        Scaffold { innerPadding ->
            val topPadding = innerPadding.calculateTopPadding()

            Column(modifier = Modifier.padding(top = topPadding)) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        coroutineScope.launch { viewModel.searchRecipes(it) }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(20.dp),
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Search",
                        )
                    },
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                )


                if (searchQuery.isEmpty()) {
                    ScrollableTabRow(
                        selectedTabIndex = selectedTabIndex,
                        edgePadding = 0.dp
                    ) {
                        categories.forEachIndexed { index, category ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = { Text(category) }
                            )
                        }
                    }
                }

                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    val displayedRecipes = if (searchQuery.isEmpty()) recipes else searchResults
                    items(count = displayedRecipes.size) { index ->
                        val recipe = displayedRecipes[index]
                        RecipeCard(
                            recipe = recipe,
                            navigateToDetailsScreen = { navigateToDetailsScreen(recipe.id) },
                            onDelete = { viewModel.deleteRecipe(recipe) },
                            onFavoriteClick = { viewModel.toggleFavoriteStatus(recipe) },
                            showDeleteIcon = true
                        )
                    }
                }
            }
        }
    }
}