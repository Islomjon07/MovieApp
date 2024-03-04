package com.example.movieapp.data.cache

import com.example.movieapp.data.cache.dao.MovieCacheModel
import kotlinx.coroutines.flow.Flow

interface MovieCacheDataSource {

    suspend fun addMovieToCache(cacheModel: MovieCacheModel)
    fun fetchAllCacheMovies(): Flow<List<MovieCacheModel>>
    suspend fun deleteMovieById(movieId: Int)
    suspend fun clearTable()
    suspend fun getMovieById(movieId: Int): MovieCacheModel

    fun observeIsMovieSaved(movieId: Int): Flow<Boolean>
}