package com.example.movieapp.presentation.screens.main_screen

import com.example.movieapp.presentation.models.MovieUi
import javax.annotation.concurrent.Immutable
import kotlinx.collections.immutable.ImmutableList
@Immutable
sealed class MainScreenUiState {

    @Immutable
    data class Success(
        val movieTopRated: ImmutableList<MovieUi>,
        val moviePopular: ImmutableList<MovieUi>,
        val movieNowPlaying: ImmutableList<MovieUi>,
        val movieUpcoming: ImmutableList<MovieUi>,
    ) : MainScreenUiState()

    data object Loading : MainScreenUiState()

    @Immutable
    data class Error(
        val message: String
    ) : MainScreenUiState()
}

