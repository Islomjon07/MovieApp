package com.example.movieapp.di

import com.example.movieapp.domain.repository.GetCurrentMovieRepository
import com.example.movieapp.domain.use_cases.now_playing.DefaultNowPlayingMovieUseCase
import com.example.movieapp.domain.use_cases.now_playing.GetNowPlayingMovieUseCase
import com.example.movieapp.domain.use_cases.popular.DefaultGetPopularMovieUseCase
import com.example.movieapp.domain.use_cases.popular.GetPopularMovieUseCase
import com.example.movieapp.domain.use_cases.top_rated.DefaultGetTopRatedMovieUseCase
import com.example.movieapp.domain.use_cases.top_rated.GetTopRatedMovieUseCase
import com.example.movieapp.domain.use_cases.upcoming.DefaultGetUpcomingMovieUseCase
import com.example.movieapp.domain.use_cases.upcoming.GetUpcomingMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetTopRatedUseCase(
        repository: GetCurrentMovieRepository
    ): GetTopRatedMovieUseCase = DefaultGetTopRatedMovieUseCase(
        repository = repository
    )

    @Provides
    fun provideGetPopularUseCase(
        repository: GetCurrentMovieRepository
    ): GetPopularMovieUseCase = DefaultGetPopularMovieUseCase(
        repository = repository
    )

    @Provides
    fun provideGetNowPlayingUseCase(
        repository: GetCurrentMovieRepository
    ): GetNowPlayingMovieUseCase = DefaultNowPlayingMovieUseCase(
        repository = repository
    )

    @Provides
    fun provideUpcomingUseCase(
        repository: GetCurrentMovieRepository
    ): GetUpcomingMovieUseCase = DefaultGetUpcomingMovieUseCase(
        repository = repository
    )
}