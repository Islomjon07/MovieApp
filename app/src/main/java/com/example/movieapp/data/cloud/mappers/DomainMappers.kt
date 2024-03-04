package com.example.movieapp.data.cloud.mappers

import com.example.movieapp.data.cache.dao.MovieCacheModel
import com.example.movieapp.domain.models.details.DetailsDomainModel

fun DetailsDomainModel.toCache() = MovieCacheModel(
    posterPath = posterPath.orEmpty(),
    overview = overview,
    releaseDate = releaseDate,
    id = id,
    originalLanguage = originalLanguage,
    title = title,
    backdropPath = backdropPath.orEmpty(),
    popularity = popularity,
    voteCount = voteCount,
    originalTitle = originalTitle,
    voteAverage = voteAverage,
    runtime = runtime
)

fun MovieCacheModel.toDomain() = DetailsDomainModel(
    posterPath = posterPath,
    overview = overview,
    releaseDate = releaseDate,
    id = id,
    originalLanguage = originalLanguage,
    title = title,
    backdropPath = backdropPath,
    popularity = popularity,
    voteCount = voteCount,
    originalTitle = originalTitle,
    voteAverage = voteAverage,
    runtime = runtime
)