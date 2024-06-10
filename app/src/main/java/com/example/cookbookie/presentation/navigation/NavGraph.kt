package com.example.cookbookie.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cookbookie.presentation.screens.RecipeDetailScreen
import com.example.cookbookie.presentation.screens.RecipeListScreen
import com.example.cookbookie.util.Routes

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.RECIPE_LIST_SCREEN
    ) {
        composable(
            route = Routes.RECIPE_LIST_SCREEN
        ) {
            RecipeListScreen(
                navigateToDetailsScreen = { recipeId ->
                    navController.navigate(
                        route = "${Routes.RECIPE_DETAIL_SCREEN}/${recipeId}"
                    )
                },
                onBackCLick = { navController.navigateUp() }
            )
        }
        composable(
            route = "${Routes.RECIPE_DETAIL_SCREEN}/{recipeId}",
            arguments = listOf(
                navArgument("recipeId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: -1
            RecipeDetailScreen(
                itemId = recipeId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}