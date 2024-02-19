package com.example.movieapp.domain.use_cases.popular

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository


class DefaultGetPopularMovieUseCase(
    private val repository: GetCurrentMovieRepository
) : GetPopularMovieUseCase {


    override suspend fun invoke(): ResultStatus<List<MovieDomain>> {
        val response = repository.popularMovieData()

        return ResultStatus(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable,
        )
    }
}