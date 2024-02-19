package com.example.movieapp.data.cloud.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieModel(
    @SerializedName("results")
    val movies: List<MovieResultList>
) : Serializable
