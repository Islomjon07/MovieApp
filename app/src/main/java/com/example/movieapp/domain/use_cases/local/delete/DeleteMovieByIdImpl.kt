package com.example.movieapp.domain.use_cases.local.delete

import com.example.movieapp.domain.repository.GetCurrentMovieRepository

class DeleteMovieByIdImpl(
    private val repository: GetCurrentMovieRepository
) : DeleteMovieById {
    override suspend fun invoke(movieId: Int) {
        return repository.deleteMovieById(movieId)
    }
}