package com.example.movieapp.presentation.screens.watch_list_screen

import com.example.movieapp.presentation.models.details.DetailsUiModel

sealed class WatchListScreenUiState {

    data class Success(
        val movie: List<DetailsUiModel>,
        val isSaved: Boolean = false,
    ) : WatchListScreenUiState()

    data object Loading : WatchListScreenUiState()

    data class Error(
        val message: String
    ) : WatchListScreenUiState()
}
