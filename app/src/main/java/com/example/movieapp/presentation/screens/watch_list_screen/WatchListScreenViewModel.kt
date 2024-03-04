package com.example.movieapp.presentation.screens.watch_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.cloud.mappers.toUiModel
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.use_cases.local.observe.FetchAllSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class WatchListScreenViewModel @Inject constructor(
    private val fetchAllSaveUseCase: FetchAllSaveUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<WatchListScreenUiState> =
        MutableStateFlow(WatchListScreenUiState.Loading)
    val uiState: StateFlow<WatchListScreenUiState> = _uiState.asStateFlow()


    private val cacheMoviesFlow = fetchAllSaveUseCase.invoke()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    init {
        cacheMoviesFlow
            .filterNotNull()
            .distinctUntilChanged()
            .onEach(::updateUiState)
            .launchIn(viewModelScope)
    }

    private fun updateUiState(cacheMovies: List<DetailsDomainModel>) {
        _uiState.update {
            WatchListScreenUiState.Success(movie = cacheMovies.map { it.toUiModel() })
        }
    }
}
