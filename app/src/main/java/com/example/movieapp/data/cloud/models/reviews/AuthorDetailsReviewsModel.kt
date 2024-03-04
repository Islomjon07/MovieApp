package com.example.movieapp.data.cloud.models.reviews


import com.google.gson.annotations.SerializedName

data class AuthorDetailsReviewsModel(
    @SerializedName("avatar_path")
    val avatarPath: Any?,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("username")
    val username: String
)