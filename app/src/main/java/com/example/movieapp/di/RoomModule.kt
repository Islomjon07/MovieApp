package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.cache.dao.MovieDao
import com.example.movieapp.data.cache.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideMoviesDao(
        database: MovieDatabase
    ): MovieDao = database.moviesDao()

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase = Room.databaseBuilder(
        context = context, MovieDatabase::class.java,"movie_database"
    ).build()

}