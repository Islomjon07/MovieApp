package com.example.movieapp.presentation.models.details

import java.io.Serializable


data class ProductionCompanyUiModel(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
) : Serializable