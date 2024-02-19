package com.example.movieapp.presentation.screens.seach_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen() {
    Text(
        modifier = Modifier.fillMaxSize().wrapContentHeight(),
        text = "SearchScreen",
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        color = Color.White
    )
}