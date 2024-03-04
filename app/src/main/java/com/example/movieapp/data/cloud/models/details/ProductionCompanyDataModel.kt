package com.example.movieapp.data.cloud.models.details


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductionCompanyDataModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
) : Serializable {
    companion object {
        val unknown = ProductionCompanyDataModel(
            id = 0,
            logoPath = "null",
            name = "",
            originCountry = ""
        )
    }
}