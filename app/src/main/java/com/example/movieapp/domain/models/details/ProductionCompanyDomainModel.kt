package com.example.movieapp.domain.models.details

import java.io.Serializable


data class ProductionCompanyDomainModel(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
) : Serializable