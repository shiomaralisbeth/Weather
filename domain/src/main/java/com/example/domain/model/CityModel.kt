package com.example.domain.model

data class CityModel(
    val name: String?= null,
    val latitude: Double,
    val longitude: Double,
    val country: String?= null,
    val state: String?= null,
)
