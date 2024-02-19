package com.example.movieapp.data.cloud.models

import com.example.movieapp.data.cloud.utils.Constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MovieResultList(
    @SerializedName("backdrop_path")
    val movieBackdropPath: String,
    @SerializedName("genre_ids")
    val movieGenreIds: List<Int>,
    @SerializedName("id")
    val movieId: Int,
    @SerializedName("original_language")
    val movieOriginalLanguage: String,
    @SerializedName("original_title")
    val movieOriginalTitle: String,
    @SerializedName("overview")
    val movieOverview: String,
    @SerializedName("popularity")
    val moviePopularity: Double,
    @SerializedName("poster_path")
    val moviePosterPath: String,
    @SerializedName("release_date")
    val movieReleaseDate: String,
    @SerializedName("title")
    val movieTitle: String,
    @SerializedName("vote_average")
    val movieVoteAverage: Double,
    @SerializedName("vote_count")
    val movieVoteCount: Int,
) : Serializable {
    companion object {
        val unknown = MovieResultList(
            movieBackdropPath = EMPTY_STRING,
            movieGenreIds = listOf(0),
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
