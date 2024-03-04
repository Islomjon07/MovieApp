package com.example.movieapp.domain.use_cases.local.observe

import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain
import kotlinx.coroutines.flow.Flow

interface FetchAllSaveUseCase {

    operator fun invoke(): Flow<List<DetailsDomainModel>>
}