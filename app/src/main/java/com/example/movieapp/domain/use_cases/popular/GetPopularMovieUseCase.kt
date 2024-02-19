package com.example.movieapp.domain.use_cases.popular

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.MovieDomain

interface GetPopularMovieUseCase {
    suspend operator fun invoke(): ResultStatus<List<MovieDomain>>
}

