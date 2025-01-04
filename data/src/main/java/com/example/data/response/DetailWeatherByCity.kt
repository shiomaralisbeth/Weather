package com.example.data.response

import com.google.gson.annotations.SerializedName

data class DetailWeatherByCity (
    @SerializedName("timezone")
    val timezone: String?= null,
    @SerializedName("current")
    val current: Current?= null,
)

data class Current(
    @SerializedName("temp")
    val temperature: String?= null,
    @SerializedName("weather")
    val weather: List<Weather>?= null
)

data class Weather(
    @SerializedName("main")
    val main: String?= null,
    @SerializedName("description")
    val description: String?= null,
    @SerializedName("icon")
    val icon: String?= null,
)