package com.example.movieapp.presentation.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieapp.presentation.nav_graf.BottomScreens
import com.example.movieapp.presentation.theme.SelectedColor
import com.example.movieapp.presentation.theme.UnSelectedColor

@Composable
fun BottomNavGraphUI(
    navController: NavController,
) {

    val listItems = listOf(
        BottomScreens.MainScreen,
        BottomScreens.SearchScreen,
        BottomScreens.WatchListScreen
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        val backStateEmpty by navController.currentBackStackEntryAsState()
        val currentRoute = backStateEmpty?.destination?.route
        listItems.forEach { items ->
            BottomNavigationItem(
                selected = currentRoute == items.route,
                onClick = {
                    navController.navigate(items.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = items.iconId),
                        contentDescription = null,
                        tint = if (currentRoute == items.route)
                            SelectedColor else UnSelectedColor
                    )
                },
                label = {
                    Text(
                        text = items.title,
                        fontSize = 10.sp,
                        color = if (currentRoute == items.route)
                            SelectedColor else UnSelectedColor
                    )
                },
                selectedContentColor = SelectedColor,
                unselectedContentColor = UnSelectedColor
            )
        }
    }
}