package com.example.movieapp.presentation.models.details

import java.io.Serializable


data class BelongsToCollectionUi(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val posterPath: String
) : Serializable