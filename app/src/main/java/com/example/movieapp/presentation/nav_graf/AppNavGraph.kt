package com.example.movieapp.presentation.nav_graf

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.presentation.screens.splash_screen.component.SplashDestination
import com.example.movieapp.presentation.screens.splash_screen.SplashScreen


@Composable
fun MovieNavGraph(
    navHostController: NavHostController,
    callBackPopBackSplash: () -> Unit,
) {
    val bottomNavigationRoute = Screens.BottomNavigation.route

    NavHost(
        navController = navHostController,
        startDestination = SplashDestination.route,
    ) {

        composable(SplashDestination.route) {
            SplashScreen(callbackScreen = { navHostController.navigate(bottomNavigationRoute) })
        }
        composable(bottomNavigationRoute) {
            MainScreenBottomNavGraph(
                callBackPopBackSplash = callBackPopBackSplash,
            )
        }
    }
}


