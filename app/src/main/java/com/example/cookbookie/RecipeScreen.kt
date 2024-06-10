package com.example.cookbookie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(RecipeEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add contact"
                )
            }
            // TODO
        },
        modifier = Modifier.padding(16.dp)
    ) { padding ->

        if (state.isAddingRecipe) {
            AddRecipeDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.recipes) { recipe ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // TODO: Image
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = recipe.title,
                            fontSize = 20.sp
                        )
                        Text(
                            text = recipe.category,
                            fontSize = 20.sp
                        )
                        Text(
                            text = recipe.instructions,
                            fontSize = 12.sp,
                            maxLines = 2
                        )
                    }
                    IconButton(onClick = {
                        onEvent(RecipeEvent.DeleteRecipe(recipe))
                    }) {
                        Icon(imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact")
                    }
                }
            }
        }
    }
}