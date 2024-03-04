package com.example.movieapp.presentation.screens.seach_screen

import com.example.movieapp.presentation.models.movie_list.MovieUi
import kotlinx.collections.immutable.ImmutableList
import javax.annotation.concurrent.Immutable

@Immutable
sealed class SearchUiState {

    data object Initial : SearchUiState()

    @Immutable
    data class Success(
        val searchUiState: ImmutableList<MovieUi>,
        val query: String
    ) : SearchUiState()

    data object Loading : SearchUiState()

    @Immutable
    data class Error(
        val message: String
    ) : SearchUiState()
}
