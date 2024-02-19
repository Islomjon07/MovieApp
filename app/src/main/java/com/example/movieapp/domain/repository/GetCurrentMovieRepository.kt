package com.example.movieapp.domain.repository

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.MovieDomain

interface GetCurrentMovieRepository {

    suspend fun topRatedMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun popularMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun nowPlayingMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun upcomingMovieData(): ResultStatus<List<MovieDomain>>
}