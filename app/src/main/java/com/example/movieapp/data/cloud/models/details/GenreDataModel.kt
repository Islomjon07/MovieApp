package com.example.movieapp.data.cloud.models.details


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenreDataModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) : Serializable {
    companion object {
        val unknown = GenreDataModel(
            id = 0,
            name = ""
        )
    }
}