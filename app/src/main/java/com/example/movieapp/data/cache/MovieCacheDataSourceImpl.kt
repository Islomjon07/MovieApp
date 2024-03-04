package com.example.movieapp.data.cache

import android.util.Log
import com.example.movieapp.data.cache.dao.MovieCacheModel
import com.example.movieapp.data.cache.dao.MovieDao
import kotlinx.coroutines.flow.Flow

class MovieCacheDataSourceImpl(
    private val movieDao: MovieDao
) : MovieCacheDataSource {

    override suspend fun addMovieToCache(cacheModel: MovieCacheModel) {
        Log.d("VVV","$cacheModel")
        movieDao.addMovieToCache(cacheModel)
    }

    override fun fetchAllCacheMovies(): Flow<List<MovieCacheModel>> {
        return movieDao.fetchAllMoviesFromCache()
    }

    override suspend fun deleteMovieById(movieId: Int) {
        movieDao.deleteMovieById(movieId)
    }

    override suspend fun clearTable() {
        movieDao.clearTable()
    }

    override suspend fun getMovieById(movieId: Int): MovieCacheModel {
        TODO("Not yet implemented")
    }

    override fun observeIsMovieSaved(movieId: Int): Flow<Boolean> {
        return movieDao.observeIsMovieSaved(movieId)
    }
}