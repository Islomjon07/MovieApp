package com.example.movieapp.domain.use_cases.now_playing

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.movie_list.MovieDomain
import com.example.movieapp.domain.repository.GetCurrentMovieRepository

class DefaultNowPlayingMovieUseCase(
    private val repository: GetCurrentMovieRepository
) : GetNowPlayingMovieUseCase {
    override suspend fun invoke(): ResultStatus<List<MovieDomain>> {
        val response = repository.nowPlayingMovieData()

        return ResultStatus(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable,
        )
    }
}
