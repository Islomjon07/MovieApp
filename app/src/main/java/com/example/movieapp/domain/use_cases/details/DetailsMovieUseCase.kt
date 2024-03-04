package com.example.movieapp.domain.use_cases.details

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain

interface DetailsMovieUseCase {
    suspend operator fun invoke(
        movieId: Int
    ): ResultStatus<DetailsDomainModel>
}



