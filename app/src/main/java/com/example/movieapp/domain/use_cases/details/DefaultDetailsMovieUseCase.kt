package com.example.movieapp.domain.use_cases.details

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository

class DefaultDetailsMovieUseCase(
    private val repository: GetCurrentMovieRepository
) : DetailsMovieUseCase {
    override suspend fun invoke(movieId: Int): ResultStatus<DetailsDomainModel> {
        val response = repository.fetchMovieByIdInfo(movieId = movieId)

        return ResultStatus(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable,
        )
    }
}
