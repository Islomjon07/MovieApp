package com.example.movieapp.domain.use_cases.upcoming

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.movie_list.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository

class DefaultGetUpcomingMovieUseCase(
    private val repository: GetCurrentMovieRepository
) : GetUpcomingMovieUseCase {
    override suspend fun invoke(): ResultStatus<List<MovieDomain>> {
        val response = repository.upcomingMovieData()

        return ResultStatus(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable,
        )
    }
}
