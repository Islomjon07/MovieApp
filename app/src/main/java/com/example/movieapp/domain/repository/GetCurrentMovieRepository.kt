package com.example.movieapp.domain.repository

import com.example.movieapp.data.cloud.base.model.ResultStatus
import com.example.movieapp.domain.models.details.DetailsDomainModel
import com.example.movieapp.domain.models.movie_list.MovieDomain
import kotlinx.coroutines.flow.Flow

interface GetCurrentMovieRepository {

    suspend fun topRatedMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun popularMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun nowPlayingMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun upcomingMovieData(): ResultStatus<List<MovieDomain>>

    suspend fun fetchSearchMovie(textQuery: String): ResultStatus<List<MovieDomain>>

    suspend fun fetchMovieByIdInfo(movieId: Int): ResultStatus<DetailsDomainModel>


    /** local room **/

    suspend fun saveMovieToCache(model: DetailsDomainModel)

    fun fetchAllSaveMovies(): Flow<List<DetailsDomainModel>>

    suspend fun deleteMovieById(movieId: Int)


    fun observeIsMovieSaved(movieId: Int): Flow<Boolean>


}