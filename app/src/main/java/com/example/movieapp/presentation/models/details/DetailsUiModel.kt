package com.example.movieapp.presentation.models.details

import java.io.Serializable


data class DetailsUiModel(
    val backdropPath: String?,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val runtime: Int,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
) : Serializable {
    companion object {
        val unknown = DetailsUiModel(
            backdropPath = "",
            id = 0,
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            releaseDate = "",
            runtime = 0,
            title = "",
            voteAverage = 0.0,
            voteCount = 0,
        )
    }
}