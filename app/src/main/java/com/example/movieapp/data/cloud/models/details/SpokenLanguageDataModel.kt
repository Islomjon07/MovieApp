package com.example.movieapp.data.cloud.models.details

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SpokenLanguageDataModel(
    @SerializedName("english_name")
    val englishName: String,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
) : Serializable {
    companion object {
        val unknown = SpokenLanguageDataModel(
            englishName = "",
            iso6391 = "",
            name = ""
        )
    }
}