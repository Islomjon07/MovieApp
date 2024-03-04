package com.example.movieapp.domain.use_cases.search

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.movie_list.MovieDomain

interface SearchMovieUseCase {
    suspend operator fun invoke(textQuery: String): ResultStatus<List<MovieDomain>>
}

