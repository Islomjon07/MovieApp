package com.example.movieapp.presentation.nav_graf

import androidx.activity.addCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.presentation.screens.splash_screen.SplashScreen


@Composable
fun MovieNavGraph(
    navHostController: NavHostController,
    callBackPopBackSplash: () -> Unit
) {
    val splashScreenRoute = Screens.SplashScreen.route
    val bottomNavigationRoute = Screens.BottomNavigation.route

    val backstackDispatcher = LocalOnBackPressedDispatcherOwner.current
    backstackDispatcher?.onBackPressedDispatcher?.addCallback {
        callBackPopBackSplash()
    }
    NavHost(
        navController = navHostController,
        startDestination = splashScreenRoute,
    ) {

        composable(splashScreenRoute) {
            SplashScreen(callbackScreen = { navHostController.navigate(bottomNavigationRoute) })
        }
        composable(bottomNavigationRoute) {
            MainScreenBottomNavGraph(
                callBackPopBackSplash = callBackPopBackSplash
            )
        }
    }
}


