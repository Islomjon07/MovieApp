package com.example.movieapp.presentation.screens.seach_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.cloud.base.model.ResponseStatus
import com.example.movieapp.data.cloud.mappers.toUiModel
import com.example.movieapp.domain.use_cases.search.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val search = MutableStateFlow("")

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        startSearch(query = search.value)
    }

    fun startSearch(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.tryEmit(SearchUiState.Loading)
            val response = searchMovieUseCase(textQuery = query)
            if (response.status == ResponseStatus.SUCCESS) {
                response.data?.let { searchMovie ->
                    _uiState.update {
                        SearchUiState.Success(
                            searchUiState = searchMovie.sortedBy { it.movieTitle }
                                .map { it.toUiModel() }.toImmutableList(), query = query
                        )
                    }
                }
            } else {
                _uiState.tryEmit(SearchUiState.Error("message"))
            }
        }
    }
}



