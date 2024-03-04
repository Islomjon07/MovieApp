package com.example.movieapp.domain.models.movie_list

import com.example.movieapp.data.cloud.utils.Constants
import java.io.Serializable

data class MovieDomain(
    val movieBackdropPath: String?,
    val movieId: Int,
    val movieOriginalLanguage: String,
    val movieOriginalTitle: String,
    val movieOverview: String,
    val moviePopularity: Double,
    val moviePosterPath: String,
    val movieReleaseDate: String,
    val movieTitle: String?,
    val movieVoteAverage: Double,
    val movieVoteCount: Int,
) : Serializable {
    companion object {
        val unknown = MovieDomain(
            movieBackdropPath = Constants.EMPTY_STRING,
            movieOriginalLanguage = Constants.EMPTY_STRING,
            movieId = 0,
            movieOriginalTitle = Constants.EMPTY_STRING,
            movieOverview = Constants.EMPTY_STRING,
            moviePopularity = 0.0,
            moviePosterPath = Constants.EMPTY_STRING,
            movieReleaseDate = Constants.EMPTY_STRING,
            movieTitle = Constants.EMPTY_STRING,
            movieVoteAverage = 0.0,
            movieVoteCount = 0
        )
    }
}

