package com.example.movieapp.domain.models.details

import java.io.Serializable


data class DetailsDomainModel(
    val adult: Boolean,
    val backdropPath: String?,
    val budget: Int,
    val genres: List<GenreModelDomain>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyDomainModel>,
    val productionCountries: List<ProductionCountryDomainModel>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageDomainModel>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) : Serializable