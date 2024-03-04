package com.example.movieapp.presentation.screens.datails_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.cloud.base.model.ResponseStatus
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.use_cases.details.DetailsMovieUseCase
import com.example.movieapp.domain.use_cases.local.delete.DeleteMovieById
import com.example.movieapp.domain.use_cases.local.observe.ObserveIsMovieSavedById
import com.example.movieapp.domain.use_cases.local.save.SaveMovieToCacheUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsMovieUseCase: DetailsMovieUseCase,
    private val saveMovieToCacheUseCase: SaveMovieToCacheUseCase,
    private val observeIsMovieSavedById: ObserveIsMovieSavedById,
    private val deleteMovieById: DeleteMovieById,
) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailsScreenUiState> =
        MutableStateFlow(DetailsScreenUiState.Loading)
    val uiState: StateFlow<DetailsScreenUiState> = _uiState.asStateFlow()

    private val handle = CoroutineExceptionHandler { _, _ -> }
    fun saveMovieToCache(movieDomain: DetailsDomainModel) {
        saveOrDeleteMovie(movieDomain)
    }

    private fun saveOrDeleteMovie(
        movieDomain: DetailsDomainModel,
    ) = viewModelScope.launch {
        val uiStateValue = _uiState.value as DetailsScreenUiState.Success
        if (uiStateValue.isSaved) {
            deleteMovieById(movieDomain.id)
        } else {
            saveMovieToCacheUseCase(movieDomain)
        }
    }

    private fun checkIsMovieSaved(movieId: Int) {
        observeIsMovieSavedById(movieId).onEach { isSaved ->
            val uiStateValue = _uiState.value as DetailsScreenUiState.Success
            _uiState.update {
                uiStateValue.copy(isSaved = isSaved)
            }
        }.launchIn(viewModelScope)
    }

    fun getDetailsMovie(movieId: Int) {
        viewModelScope.launch(handle + Dispatchers.IO) {

            val movieDetails = async {
                detailsMovieUseCase(movieId = movieId)
            }

            val movie = movieDetails.await()

            if (movie.status == ResponseStatus.SUCCESS) {
                movie.data?.let { details ->
                    _uiState.update {
                        DetailsScreenUiState.Success(
                            movieDetails = details,
                        )
                    }
                    checkIsMovieSaved(details.id)
                }
            }
        }
    }
}