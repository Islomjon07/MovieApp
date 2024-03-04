package com.example.movieapp.domain.use_cases.search

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.movie_list.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository


class DefaultSearchMovieUseCase(
    private val repository: GetCurrentMovieRepository
) : SearchMovieUseCase {

    override suspend fun invoke(textQuery: String): ResultStatus<List<MovieDomain>> {
        val response = repository.fetchSearchMovie(textQuery)

        return ResultStatus(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable,
        )
    }
}