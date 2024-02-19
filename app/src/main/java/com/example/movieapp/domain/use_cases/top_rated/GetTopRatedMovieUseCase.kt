package com.example.movieapp.domain.use_cases.top_rated

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.MovieDomain

interface GetTopRatedMovieUseCase {
    suspend operator fun invoke(): ResultStatus<List<MovieDomain>>
}
