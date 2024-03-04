package com.example.movieapp.domain.models.details

import java.io.Serializable


data class DetailsDomainModel(
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
) : Serializable