package com.example.movieapp.presentation.screens.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.cloud.base.model.ResponseStatus
import com.example.movieapp.data.cloud.mappers.toUiModel
import com.example.movieapp.domain.use_cases.now_playing.GetNowPlayingMovieUseCase
import com.example.movieapp.domain.use_cases.popular.GetPopularMovieUseCase
import com.example.movieapp.domain.use_cases.top_rated.GetTopRatedMovieUseCase
import com.example.movieapp.domain.use_cases.upcoming.GetUpcomingMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainScreenUiState> =
        MutableStateFlow(MainScreenUiState.Loading)
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    init {
        fetchTopRatedMovie()
    }
    fun fetchTopRatedMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.tryEmit(MainScreenUiState.Loading)

            val topRatedMovieResponse = getTopRatedMovieUseCase()
            val popularMovieResponse = getPopularMovieUseCase()
            val nowPlayingMovieResponse = getNowPlayingMovieUseCase()
            val upcomingMovieResponse = getUpcomingMovieUseCase()

            if (topRatedMovieResponse.status == ResponseStatus.SUCCESS &&
                popularMovieResponse.status == ResponseStatus.SUCCESS &&
                nowPlayingMovieResponse.status == ResponseStatus.SUCCESS &&
                upcomingMovieResponse.status == ResponseStatus.SUCCESS
            ) {
                val topRatedMovies = topRatedMovieResponse.data?.map { it.toUiModel() } ?: emptyList()
                val popularMovies = popularMovieResponse.data?.map { it.toUiModel() } ?: emptyList()
                val nowPlayingMovies = nowPlayingMovieResponse.data?.map { it.toUiModel() } ?: emptyList()
                val upcomingMovies = upcomingMovieResponse.data?.map { it.toUiModel() } ?: emptyList()

                val uiState = MainScreenUiState.Success(
                    movieTopRated = topRatedMovies.toImmutableList(),
                    moviePopular = popularMovies.toImmutableList(),
                    movieNowPlaying = nowPlayingMovies.toImmutableList(),
                    movieUpcoming = upcomingMovies.toImmutableList(),
                )
                _uiState.tryEmit(uiState)
            } else {
                _uiState.tryEmit(MainScreenUiState.Error("message"))
            }
        }
    }
}