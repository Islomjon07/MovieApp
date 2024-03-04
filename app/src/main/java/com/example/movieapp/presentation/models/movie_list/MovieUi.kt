package com.example.movieapp.presentation.models.movie_list

import androidx.compose.runtime.Stable
import com.example.movieapp.data.cloud.utils.Constants.EMPTY_STRING
import java.io.Serializable

@Stable
data class MovieUi(
    val movieBackdropPath: String,
    val movieId: Int,
    val movieOriginalLanguage: String,
    val movieOriginalTitle: String,
    val movieOverview: String,
    val moviePopularity: Double,
    val moviePosterPath: String,
    val movieReleaseDate: String,
    val movieTitle: String,
    val movieVoteAverage: Double,
    val movieVoteCount: Int,
) : Serializable {
    companion object {
        val unknown = MovieUi(
            movieBackdropPath = EMPTY_STRING,
            movieOriginalLanguage = EMPTY_STRING,
            movieId = 0,
            movieOriginalTitle = EMPTY_STRING,
            movieOverview = EMPTY_STRING,
            moviePopularity = 0.0,
            moviePosterPath = EMPTY_STRING,
            movieReleaseDate = EMPTY_STRING,
            movieTitle = EMPTY_STRING,
            movieVoteAverage = 0.0,
            movieVoteCount = 0
        )
    }
}
