package com.example.movieapp.domain.models.details

import java.io.Serializable

data class SpokenLanguageDomainModel(
    val englishName: String,
    val iso6391: String,
    val name: String
) : Serializable