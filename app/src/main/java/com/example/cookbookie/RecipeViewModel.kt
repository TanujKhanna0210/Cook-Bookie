package com.example.cookbookie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val dao: RecipeDao
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), RecipeState())

    fun onEvent(event: RecipeEvent) {
        when (event) {
            is RecipeEvent.DeleteRecipe -> {
                viewModelScope.launch {
                    dao.deleteRecipe(event.recipe)
                }
            }

            RecipeEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingRecipe = false
                    )
                }
            }

            RecipeEvent.SaveRecipe -> {
                val title = state.value.title
                val category = state.value.category
                val ingredients = state.value.ingredients
                val instructions = state.value.instructions
//                val image = state.value.image

                if (title.isBlank() || category.isBlank() || ingredients.isBlank() || instructions.isBlank()) {
                    return
                }

                val recipe = Recipe(
                    title = title,
                    category = category,
                    ingredients = ingredients,
                    instructions = instructions,
//                    image = image
                )

                viewModelScope.launch {
                    dao.upsertRecipe(recipe)
                }
                _state.update {
                    it.copy(
                        isAddingRecipe = false,
                        title = "",
                        category = "",
                        ingredients = "",
                        instructions = "",
//                        image = ,
                        )
                }

            }

            is RecipeEvent.SetCategory -> {
                _state.update {
                    it.copy(
                        category = event.category
                    )
                }
            }

//            is RecipeEvent.SetImage -> {
//                _state.update {
//                    it.copy(
//                        image = event.image
//                    )
//                }
//            }

            is RecipeEvent.SetIngredients -> {
                _state.update {
                    it.copy(
                        ingredients = event.ingredients
                    )
                }
            }

            is RecipeEvent.SetInstructions -> {
                _state.update {
                    it.copy(
                        instructions = event.instructions
                    )
                }
            }

            is RecipeEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }

            RecipeEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingRecipe = true
                    )
                }
            }
        }
    }
}