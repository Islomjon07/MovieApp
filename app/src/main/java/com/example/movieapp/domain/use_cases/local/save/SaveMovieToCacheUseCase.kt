package com.example.movieapp.domain.use_cases.local.save

import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain

interface SaveMovieToCacheUseCase {

    suspend operator fun invoke(movieDomainModel: DetailsDomainModel)
}