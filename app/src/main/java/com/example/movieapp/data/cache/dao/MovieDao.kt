package com.example.movieapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(entity = MovieCacheModel::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToCache(cacheModel: MovieCacheModel)

    @Query("SELECT * FROM movies_table_name")
    fun fetchAllMoviesFromCache(): Flow<List<MovieCacheModel>>

    @Query("DELETE FROM movies_table_name WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM movies_table_name WHERE id = :movieId  LIMIT 1)")
    fun observeIsMovieSaved(movieId: Int): Flow<Boolean>

    @Query("DELETE FROM movies_table_name")
    suspend fun clearTable()

}

