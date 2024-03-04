package com.example.movieapp.presentation.models.details

import java.io.Serializable

data class SpokenLanguageUiModel(
    val englishName: String,
    val iso6391: String,
    val name: String
) : Serializable