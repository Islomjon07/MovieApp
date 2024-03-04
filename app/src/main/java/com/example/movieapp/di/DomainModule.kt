package com.example.movieapp.di

import com.example.movieapp.domain.repository.GetCurrentMovieRepository
import com.example.movieapp.domain.use_cases.details.DefaultDetailsMovieUseCase
import com.example.movieapp.domain.use_cases.details.DetailsMovieUseCase
import com.example.movieapp.domain.use_cases.local.delete.DeleteMovieById
import com.example.movieapp.domain.use_cases.local.delete.DeleteMovieByIdImpl
import com.example.movieapp.domain.use_cases.local.observe.FetchAllSaveUseCase
import com.example.movieapp.domain.use_cases.local.observe.FetchAllSaveUseCaseImpl
import com.example.movieapp.domain.use_cases.local.observe.ObserveIsMovieSavedById
import com.example.movieapp.domain.use_cases.local.observe.ObserveIsMovieSavedByIdImpl
import com.example.movieapp.domain.use_cases.local.save.SaveMovieToCacheUseCase
import com.example.movieapp.domain.use_cases.local.save.SaveMovieToCacheUseCaseImpl
import com.example.movieapp.domain.use_cases.now_playing.DefaultNowPlayingMovieUseCase
import com.example.movieapp.domain.use_cases.now_playing.GetNowPlayingMovieUseCase
import com.example.movieapp.domain.use_cases.popular.DefaultGetPopularMovieUseCase
import com.example.movieapp.domain.use_cases.popular.GetPopularMovieUseCase
import com.example.movieapp.domain.use_cases.search.DefaultSearchMovieUseCase
import com.example.movieapp.domain.use_cases.search.SearchMovieUseCase
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

    @Provides
    fun provideDetailsUseCase(
        repository: GetCurrentMovieRepository
    ): DetailsMovieUseCase = DefaultDetailsMovieUseCase(
        repository = repository
    )

    @Provides
    fun provideSearchUseCase(
        repository: GetCurrentMovieRepository
    ): SearchMovieUseCase = DefaultSearchMovieUseCase(
        repository = repository
    )

    @Provides
    fun provideSaveToMovieUseCase(
        repository: GetCurrentMovieRepository
    ): SaveMovieToCacheUseCase = SaveMovieToCacheUseCaseImpl(
        movieRepository = repository
    )

    @Provides
    fun provideFetchSaveToMovieUseCase(
        repository: GetCurrentMovieRepository
    ): FetchAllSaveUseCase = FetchAllSaveUseCaseImpl(
        movieRepository = repository
    )

    @Provides
    fun provideObserveIsMovieSavedByIdUseCase(
        repository: GetCurrentMovieRepository
    ): ObserveIsMovieSavedById = ObserveIsMovieSavedByIdImpl(
        repository = repository
    )

    @Provides
    fun provideDeleteMovieByIdUseCase(
        repository: GetCurrentMovieRepository
    ): DeleteMovieById = DeleteMovieByIdImpl(
        repository = repository
    )

}

