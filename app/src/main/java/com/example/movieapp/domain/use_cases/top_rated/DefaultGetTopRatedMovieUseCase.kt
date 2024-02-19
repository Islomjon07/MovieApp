package com.example.movieapp.domain.use_cases.top_rated

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository
import javax.inject.Inject

class DefaultGetTopRatedMovieUseCase @Inject constructor(
    private val repository: GetCurrentMovieRepository
) : GetTopRatedMovieUseCase {
    override suspend fun invoke(): ResultStatus<List<MovieDomain>> {
        val response = repository.topRatedMovieData()
        return ResultStatus(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable,
        )
    }
}     
