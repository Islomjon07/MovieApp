package com.example.movieapp.domain.use_cases.upcoming

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.MovieDomain

interface GetUpcomingMovieUseCase {
    suspend operator fun invoke(): ResultStatus<List<MovieDomain>>
}



