package com.example.movieapp.domain.use_cases.local.observe

import com.example.movieapp.domain.repository.GetCurrentMovieRepository
import kotlinx.coroutines.flow.Flow

class ObserveIsMovieSavedByIdImpl(
    private val repository: GetCurrentMovieRepository
) : ObserveIsMovieSavedById {
    override fun invoke(movieId: Int): Flow<Boolean> {
        return repository.observeIsMovieSaved(movieId)
    }
}