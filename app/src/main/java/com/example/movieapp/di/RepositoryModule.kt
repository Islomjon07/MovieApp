package com.example.movieapp.di

import com.example.movieapp.data.cloud.remote.MovieService
import com.example.movieapp.data.cloud.repository.DefaultGetCurrentMovieRepository
import com.example.movieapp.domain.repository.GetCurrentMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideGetCurrentWeatherRepository(
        service: MovieService
    ): GetCurrentMovieRepository = DefaultGetCurrentMovieRepository(
        service = service
    )
}