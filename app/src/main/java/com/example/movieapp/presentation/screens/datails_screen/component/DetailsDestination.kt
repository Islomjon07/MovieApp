package com.example.movieapp.presentation.screens.datails_screen.component

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.movieapp.presentation.nav_graf.Destination

object DetailsDestination: Destination {
    override val route: String
        get() = "DetailsScreen"
    val movieId = "movieId"
    val routeWithArgs = "$route/{$movieId}"
    val arguments = listOf(navArgument(movieId) { type = NavType.IntType })
}