package com.example.movieapp.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SetSystemBarsColor(
    setSystemBarsColor: Color,
    setNavigationBarColor: Color
) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(setSystemBarsColor)
        systemUiController.setNavigationBarColor(setNavigationBarColor)
    }
}
