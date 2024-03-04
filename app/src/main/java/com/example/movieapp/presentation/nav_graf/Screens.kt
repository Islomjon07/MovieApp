package com.example.movieapp.presentation.nav_graf

import com.example.movieapp.R

sealed class Screens(val route: String) {
    data object BottomNavigation : Screens("BottomNavigation")
}
sealed class BottomScreens(val title: String, val iconId: Int, val route: String) {
    data object MainScreen : BottomScreens(
        "Home", R.drawable.home, "MainScreen"
    )

    data object SearchScreen : BottomScreens(
        "Search", R.drawable.search__4_, "SearchScreen"
    )

    data object WatchListScreen : BottomScreens(
        "Watch list", R.drawable.save__1_, "WatchListScreen"
    )
}