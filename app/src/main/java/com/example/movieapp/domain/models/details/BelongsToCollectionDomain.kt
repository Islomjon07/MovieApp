package com.example.movieapp.domain.models.details

import java.io.Serializable


data class BelongsToCollectionDomain(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val posterPath: String
) : Serializable