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
import com.example.movieapp.presentation.screens.datails_screen.DetailsScreen
import com.example.movieapp.presentation.screens.datails_screen.DetailsViewModel
import com.example.movieapp.presentation.screens.datails_screen.component.DetailsDestination
import com.example.movieapp.presentation.screens.main_screen.MainScreen
import com.example.movieapp.presentation.screens.main_screen.MainScreenViewModel
import com.example.movieapp.presentation.screens.main_screen.component.MainScreenDestination
import com.example.movieapp.presentation.screens.seach_screen.SearchScreen
import com.example.movieapp.presentation.screens.seach_screen.SearchViewModel
import com.example.movieapp.presentation.screens.seach_screen.component.SearchDestination
import com.example.movieapp.presentation.screens.watch_list_screen.WatchListScreen
import com.example.movieapp.presentation.screens.watch_list_screen.WatchListScreenViewModel
import com.example.movieapp.presentation.screens.watch_list_screen.component.WatchListDestination

@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
    callBackPopBackSplash: () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = MainScreenDestination.route,
    ) {
        composable(MainScreenDestination.route) {
            val viewModel: MainScreenViewModel = hiltViewModel()
            MainScreen(
                uiStateFlow = viewModel.uiState,
                viewModel = viewModel,
                callBackPopBackSplash = callBackPopBackSplash,
                onNavigateToInfo = { movieId ->
                    navHostController.navigate("${DetailsDestination.route}/$movieId")
                },
                onNavigateToSearch = { navHostController.navigate(SearchDestination.route) }
            )
        }
        composable(SearchDestination.route) {
            val searchViewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                uiStateFlow = searchViewModel.uiState,
                onValueChange = searchViewModel::startSearch,
                viewModel = searchViewModel,
                navigateToDetails = { searchId ->
                    navHostController.navigate("${DetailsDestination.route}/$searchId")
                },
                callBackPopBackState = { navHostController.popBackStack() }
            )
        }
        composable(WatchListDestination.route) {
            val viewModel: WatchListScreenViewModel = hiltViewModel()
            WatchListScreen(
                uiStateFlow = viewModel.uiState,
                navigateToDetails = { watchList ->
                    navHostController.navigate("${DetailsDestination.route}/$watchList")
                },
                callBackPopBackStake = { navHostController.navigate(MainScreenDestination.route) }
            )
        }
        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = DetailsDestination.arguments,
        ) { navBackStackEntry ->
            val movieId = navBackStackEntry.arguments?.getInt(DetailsDestination.movieId) ?: 0
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(
                onGetMovieInfo = { viewModel.getDetailsMovie(movieId) },
                uiStateFlow = viewModel.uiState,
                callBackPopBackDetail = { navHostController.popBackStack() },
                onSaveMovieToCache = viewModel::saveMovieToCache,
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenBottomNavGraph(
    callBackPopBackSplash: () -> Unit,
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavGraphUI(navController = navController)
        }
    ) {
        BottomNavGraph(
            navHostController = navController,
            callBackPopBackSplash = callBackPopBackSplash,
        )
    }
}