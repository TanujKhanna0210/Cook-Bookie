package com.example.cookbookie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeDialog(
    state: RecipeState,
    onEvent: (RecipeEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onEvent(RecipeEvent.HideDialog) },
        title = { Text(text = "Add recipe") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.title,
                    onValueChange = {
                        onEvent(RecipeEvent.SetTitle(it))
                    },
                    placeholder = {
                        Text(text = "Title")
                    }
                )
                TextField(
                    value = state.category,
                    onValueChange = {
                        onEvent(RecipeEvent.SetCategory(it))
                    },
                    placeholder = {
                        Text(text = "Category")
                    }
                )
                TextField(
                    value = state.ingredients,
                    onValueChange = {
                        onEvent(RecipeEvent.SetIngredients(it))
                    },
                    placeholder = {
                        Text(text = "Ingredients")
                    }
                )
                TextField(
                    value = state.instructions,
                    onValueChange = {
                        onEvent(RecipeEvent.SetInstructions(it))
                    },
                    placeholder = {
                        Text(text = "Instructions")
                    }
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = { onEvent(RecipeEvent.SaveRecipe) }) {
                    Text(text = "Save")
                }
            }
        }
    )
}