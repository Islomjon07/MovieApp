package com.example.movieapp.data.cloud.models.reviews


import com.google.gson.annotations.SerializedName

data class MovieReviewsModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultReviewsModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)