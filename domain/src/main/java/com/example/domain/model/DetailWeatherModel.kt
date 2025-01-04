package com.example.domain.model

data class DetailWeatherModel(
    val timezone: String? = null,
    val current: CurrentModel? = null,
)

data class CurrentModel(
    val temperature: String?= null,
    val weather: List<WeatherModel>? = null
)

data class WeatherModel(
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)
