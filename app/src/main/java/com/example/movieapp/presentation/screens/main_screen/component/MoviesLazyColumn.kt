package com.example.movieapp.presentation.screens.main_screen.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.movieapp.R
import com.example.movieapp.presentation.models.MovieUi
import com.example.movieapp.presentation.theme.dp5
import kotlinx.collections.immutable.ImmutableList
import androidx.compose.foundation.lazy.items

@Composable
fun MoviesLazyColumn(
    movieList: ImmutableList<MovieUi>,
    modifier: Modifier = Modifier
) {
    LazyRow {
        items(items = movieList, key = { it.movieId }) {
            Spacer(modifier = modifier.padding(dp5))
            MainScreenItem(
                imageUrl = it.moviePosterPath,
                image = painterResource(id = R.drawable.movie_svgrepo_com)
            )
        }
    }
}
