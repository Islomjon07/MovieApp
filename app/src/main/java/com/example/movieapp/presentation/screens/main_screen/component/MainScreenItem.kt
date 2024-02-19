package com.example.movieapp.presentation.screens.main_screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.data.cloud.utils.Constants.POSTER_PATH_URL
import com.example.movieapp.presentation.theme.dp14

@Composable
fun MainScreenItem(
    imageUrl: String,
    modifier: Modifier = Modifier,
    image: Painter
) {
    Box(
        modifier = modifier
            .width(144.dp)
            .height(210.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp))
    ) {
        AsyncImage(
            model = POSTER_PATH_URL + imageUrl,
            contentDescription = null,
            placeholder = image,
        )
    }
}

@Composable
fun MainScreenItemTwo(
    imageUrl: String,
    modifier: Modifier = Modifier,
    image: Painter
) {
    Box(
        modifier = modifier
            .width(80.dp)
            .height(170.dp)
            .clip(shape = RoundedCornerShape(size = dp14)),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = POSTER_PATH_URL + imageUrl,
            contentDescription = null,
            placeholder = image,
        )
    }
}