package com.example.cookbookie.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.cookbookie.RecipeViewModel
import com.example.cookbookie.domain.model.Recipe

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
fun UpdateRecipeComponent(
    viewModel: RecipeViewModel,
    recipeId: Int,
    onBackClick: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getRecipe(recipeId)
    }

    val title = viewModel.recipe.title
    val category = viewModel.recipe.category
    val ingredients = viewModel.recipe.ingredients
    val instructions = viewModel.recipe.instructions

//    val image = viewModel.recipe.image

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar(
                title = "Update Recipe",
                onBackClick = onBackClick
            )
        }
    ) {
        val topPadding = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Image:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(6.dp))

//                PickImageFromGallery(image = remember { mutableStateOf(*(viewModel.recipe.image.toTypedArray())) })

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Title:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.width(80.dp)
                )

                Spacer(modifier = Modifier.width(3.dp))

                val keyboardController = LocalSoftwareKeyboardController.current
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        if (it.length <= 50) {
                            viewModel.updateTitle(it)
                        }
                    },
                    placeholder = {
                        Text(
                            text = "*required",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                        }
                    )
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Category:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.width(80.dp)
                )

                Spacer(modifier = Modifier.width(3.dp))

                val keyboardController = LocalSoftwareKeyboardController.current
                OutlinedTextField(
                    value = category,
                    onValueChange = {
                        if (it.length <= 15) {
                            viewModel.updateCategory(it)
                        }
                    },
                    placeholder = {
                        Text(
                            text = "*required",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    })
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Ingredients:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    value = ingredients,
                    onValueChange = {
                        viewModel.updateIngredients(it)
                    },
                    placeholder = {
                        Text(
                            text = "*required", style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Instructions:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(6.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    value = instructions,
                    onValueChange = {
                        viewModel.updateInstructions(it)
                    },
                    placeholder = {
                        Text(
                            text = "*required", style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (title.isNotEmpty() && category.isNotEmpty() && ingredients.isNotEmpty() && instructions.isNotEmpty()) {
                        val recipe = Recipe(
                            id = recipeId,
                            title = title,
                            category = category,
                            ingredients = ingredients,
                            instructions = instructions,
//                            image = image
                        )
                        viewModel.upsertRecipe(recipe)
                        onBackClick()
                    } else {
                        Toast.makeText(
                            context,
                            "Please enter the required fields!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("Save")
                }
            }
        }
    }
}