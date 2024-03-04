package com.example.movieapp.domain.repository

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain

interface GetCurrentMovieRepository {

    suspend fun topRatedMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun popularMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun nowPlayingMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun upcomingMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun fetchSearchMovie(textQuery: String): ResultStatus<List<MovieDomain>>

    suspend fun fetchMovieByIdInfo(movieId: Int): ResultStatus<DetailsDomainModel>
}