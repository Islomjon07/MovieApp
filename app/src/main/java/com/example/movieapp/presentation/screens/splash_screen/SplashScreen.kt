package com.example.movieapp.presentation.screens.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.R
import com.example.movieapp.presentation.component.MovieLottieAnim
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPLASH_SCREEN_MILLIS = 2000L

@SuppressLint("ResourceType")
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    callbackScreen: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            delay(SPLASH_SCREEN_MILLIS)
            callbackScreen()
        }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieLottieAnim(rawFile = R.raw.splash_movie_anim)
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        callbackScreen = {},
    )
}

