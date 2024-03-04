package com.example.movieapp.data.cloud.models.details


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductionCountryDataModel(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
) : Serializable{
    companion object{
        val unknown = ProductionCountryDataModel(
            iso31661 = "",
            name = ""
        )
    }
}