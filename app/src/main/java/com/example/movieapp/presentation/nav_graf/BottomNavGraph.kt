package com.example.movieapp.presentation.nav_graf

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.presentation.component.BottomNavGraphUI
import com.example.movieapp.presentation.screens.main_screen.MainScreen
import com.example.movieapp.presentation.screens.main_screen.MainScreenViewModel
import com.example.movieapp.presentation.screens.seach_screen.SearchScreen
import com.example.movieapp.presentation.screens.watch_list_screen.WatchListScreen

@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
    callBackPopBackSplash: () -> Unit,
) {
    val mainScreenRoute = BottomScreens.MainScreen.route
    val searchScreenRoute = BottomScreens.SearchScreen.route
    val watchListScreen = BottomScreens.WatchListScreen.route

    val viewModel: MainScreenViewModel = hiltViewModel()

    NavHost(
        navController = navHostController,
        startDestination = mainScreenRoute,
    ) {

        composable(mainScreenRoute) {
            MainScreen(
                uiStateFlow = viewModel.uiState,
                viewModel = viewModel,
                callBackPopBackSplash = callBackPopBackSplash
            )
        }
        composable(searchScreenRoute) {
            SearchScreen()
        }
        composable(watchListScreen) {
            WatchListScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenBottomNavGraph(
    callBackPopBackSplash: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavGraphUI(navController = navController) }
    ) {
        BottomNavGraph(
            navHostController = navController,
            callBackPopBackSplash = callBackPopBackSplash
        )
    }
}