package com.example.cookbookie.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cookbookie.presentation.screens.BookmarkScreen
import com.example.cookbookie.presentation.screens.HomeScreen
import com.example.cookbookie.presentation.screens.RecipeDetailScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Filled.Home, text = "Home"),
            BottomNavigationItem(icon = Icons.Filled.Add, text = "Add"),
            BottomNavigationItem(icon = Icons.Filled.Bookmark, text = "Collection")
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.DetailsScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToDetailsScreen(
                                navController,
                                -1
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen(
                    navigateToDetailsScreen = { recipeId ->
                        navController.navigate(
                            route = "${Route.DetailsScreen.route}/${recipeId}"
                        )
                    },
                    onBackCLick = { navController.navigateUp() }
                )
            }

            composable(
                route = "${Route.DetailsScreen.route}/{recipeId}",
                arguments = listOf(
                    navArgument("recipeId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: -1
                RecipeDetailScreen(
                    recipeId = recipeId,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    navigateToHomeScreen = {
                        navController.navigate(Route.HomeScreen.route) {
                            popUpTo(Route.HomeScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(route = Route.BookmarkScreen.route) {
                BookmarkScreen(
                    navigateToDetailsScreen = {recipeId ->
                        navController.navigate(
                            route = "${Route.DetailsScreen.route}/$recipeId"
                        )
                    }
                )
            }
        }


    }
}

fun navigateToDetailsScreen(
    navController: NavController,
    recipeId: Int
) {
    val route = "${Route.DetailsScreen.route}/${recipeId}"
    navController.navigate(route = route)
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route = route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}
