package com.example.movieapp.data.cloud.mappers

import com.example.movieapp.data.cloud.models.MovieResultList
import com.example.movieapp.data.cloud.utils.Constants.POSTER_PATH_URL
import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.presentation.models.MovieUi


//
//fun MovieModel.toDataModel() = MovieDomain(
//    movies = movies.map { it.toDataModel() }
//
//)

fun MovieResultList.toDataModel() = MovieDomain(
    movieVoteCount = movieVoteCount,
    movieVoteAverage = movieVoteAverage,
    movieTitle = movieTitle,
    movieReleaseDate = movieReleaseDate,
    moviePosterPath = moviePosterPath + POSTER_PATH_URL,
    moviePopularity = moviePopularity,
    movieOverview = movieOverview,
    movieId = movieId,
    movieOriginalTitle = movieOriginalTitle,
    movieOriginalLanguage = movieOriginalLanguage,
    movieGenreIds = movieGenreIds,
    movieBackdropPath = movieBackdropPath,
)

fun MovieDomain.toUiModel() = MovieUi(
    movieVoteCount = movieVoteCount,
    movieVoteAverage = movieVoteAverage,
    movieTitle = movieTitle,
    movieReleaseDate = movieReleaseDate,
    moviePosterPath = moviePosterPath + POSTER_PATH_URL,
    moviePopularity = moviePopularity,
    movieOverview = movieOverview,
    movieId = movieId,
    movieOriginalTitle = movieOriginalTitle,
    movieOriginalLanguage = movieOriginalLanguage,
    movieGenreIds = movieGenreIds,
    movieBackdropPath = movieBackdropPath,
)


