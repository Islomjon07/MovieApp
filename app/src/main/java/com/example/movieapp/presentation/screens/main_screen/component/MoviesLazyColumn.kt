package com.example.movieapp.presentation.screens.main_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.movieapp.R
import com.example.movieapp.presentation.models.movie_list.MovieUi
import com.example.movieapp.presentation.theme.dp8
import kotlinx.collections.immutable.ImmutableList

@Composable
fun MoviesLazyColumn(
    movieList: ImmutableList<MovieUi>,
    modifier: Modifier = Modifier,
    onNavigateToInfo: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(dp8)
    ) {
        items(items = movieList, key = { it.movieId }) {
            MainScreenItem(
                imageUrl = it.moviePosterPath,
                image = painterResource(id = R.drawable.movie_svgrepo_com),
                movieID = it.movieId,
                onNavigateToInfo = onNavigateToInfo
            )
        }
    }
}
