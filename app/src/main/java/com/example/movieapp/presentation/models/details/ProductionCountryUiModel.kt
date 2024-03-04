package com.example.movieapp.presentation.models.details

import java.io.Serializable


data class ProductionCountryUiModel(
    val iso31661: String,
    val name: String
) : Serializable