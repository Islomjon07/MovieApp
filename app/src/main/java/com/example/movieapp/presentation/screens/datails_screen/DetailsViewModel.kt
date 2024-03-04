package com.example.movieapp.presentation.screens.datails_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.cloud.base.model.ResponseStatus
import com.example.movieapp.data.cloud.mappers.toUiModel
import com.example.movieapp.domain.use_cases.details.DetailsMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsMovieUseCase: DetailsMovieUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenUiState> =
        MutableStateFlow(DetailsScreenUiState.Loading)
    val uiState: StateFlow<DetailsScreenUiState> = _uiState.asStateFlow()

    fun getDetailsMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.tryEmit(DetailsScreenUiState.Loading)
            val response = async {
                detailsMovieUseCase(movieId = movieId)
            }
            val movieResponseStatus = response.await()

            if (movieResponseStatus.status == ResponseStatus.SUCCESS) {
                movieResponseStatus.data?.let { movie ->
                    _uiState.update {
                        DetailsScreenUiState.Success(
                            movieDetails = movie.toUiModel()
                        )
                    }
                }
            } else {
                _uiState.tryEmit(DetailsScreenUiState.Error("message"))
            }
        }
    }
}
