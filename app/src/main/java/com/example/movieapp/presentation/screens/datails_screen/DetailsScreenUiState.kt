package com.example.movieapp.presentation.screens.datails_screen

import com.example.movieapp.presentation.models.details.DetailsUiModel
import javax.annotation.concurrent.Immutable

@Immutable
sealed class DetailsScreenUiState {
    @Immutable
    data class Success(
        val movieDetails: DetailsUiModel
    ) : DetailsScreenUiState()

    data object Loading : DetailsScreenUiState()

    @Immutable
    data class Error(
        val message: String
    ) : DetailsScreenUiState()
}

