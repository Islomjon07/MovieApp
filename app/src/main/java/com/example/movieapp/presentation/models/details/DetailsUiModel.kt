package com.example.movieapp.presentation.models.details

import java.io.Serializable


data class DetailsUiModel(
    val adult: Boolean,
    val backdropPath: String?,
    val budget: Int,
    val genres: List<GenreModelUi>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyUiModel>,
    val productionCountries: List<ProductionCountryUiModel>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageUiModel>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) : Serializable {
    companion object {
        val unknown = DetailsUiModel(
            adult = true,
            backdropPath = "",
            budget = 0,
            genres = listOf(),
            homepage = "",
            id = 0,
            imdbId = "",
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            productionCompanies = listOf(),
            productionCountries = listOf(),
            releaseDate = "",
            revenue = 0,
            runtime = 0,
            spokenLanguages = listOf(),
            status = "",
            tagline = "",
            title = "",
            video = true,
            voteAverage = 0.0,
            voteCount = 0,
        )
    }
}