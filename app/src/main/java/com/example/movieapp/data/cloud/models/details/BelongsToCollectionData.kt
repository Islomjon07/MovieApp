package com.example.movieapp.data.cloud.models.details


import com.example.movieapp.data.cloud.utils.Constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BelongsToCollectionData(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
) : Serializable {
    companion object {
        val unknown = BelongsToCollectionData(
            backdropPath = EMPTY_STRING,
            id = 0,
            name = EMPTY_STRING,
            posterPath = EMPTY_STRING
        )
    }
}