package com.example.cookbookie.presentation.navigation

sealed class Route(
    val route: String
) {

    object HomeScreen: Route("homeScreen")
    object DetailsScreen: Route("detailsScreen")
    object SearchScreen: Route("searchScreen")
    object BookmarkScreen: Route("bookmarkScreen")

}