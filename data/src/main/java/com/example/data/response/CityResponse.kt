package com.example.data.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("name")
    val name: String?= null,
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double,
    @SerializedName("country")
    val country: String?= null,
    @SerializedName("state")
    val state: String?= null,
)
