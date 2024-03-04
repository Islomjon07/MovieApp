package com.example.movieapp.domain.models.details

import java.io.Serializable


data class ProductionCountryDomainModel(
    val iso31661: String,
    val name: String
) : Serializable