package com.example.movieapp.domain.use_cases.local.save

import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.repository.GetCurrentMovieRepository

class SaveMovieToCacheUseCaseImpl(
    private val movieRepository: GetCurrentMovieRepository
) : SaveMovieToCacheUseCase {
    override suspend fun invoke(movieDomainModel: DetailsDomainModel) {
        movieRepository.saveMovieToCache(movieDomainModel)
    }
}