package com.example.movieapp.domain.use_cases.local.observe

import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.repository.GetCurrentMovieRepository
import kotlinx.coroutines.flow.Flow


class FetchAllSaveUseCaseImpl(
    private val movieRepository: GetCurrentMovieRepository
) : FetchAllSaveUseCase {
    override fun invoke(): Flow<List<DetailsDomainModel>> {
        return movieRepository.fetchAllSaveMovies()

    }
}