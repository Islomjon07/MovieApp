package com.example.movieapp.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.data.cache.dao.MovieCacheModel
import com.example.movieapp.data.cache.dao.MovieDao

@Database(entities = [MovieCacheModel::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MovieDao
}
