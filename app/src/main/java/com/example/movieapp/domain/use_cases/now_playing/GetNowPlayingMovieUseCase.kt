package com.example.movieapp.domain.use_cases.now_playing

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.movie_list.MovieDomain

interface GetNowPlayingMovieUseCase {
    suspend operator fun invoke(): ResultStatus<List<MovieDomain>>
}



