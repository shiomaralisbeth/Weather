package com.example.data.mapper

import com.example.data.response.CityResponse
import com.example.data.response.DetailWeatherByCity
import com.example.data.response.Weather
import com.example.data.source.database.entities.SaveedCitiesEntity
import com.example.domain.model.CityModel
import com.example.domain.model.CurrentModel
import com.example.domain.model.DetailWeatherModel
import com.example.domain.model.SavedCitiesModel
import com.example.domain.model.WeatherModel

fun CityResponse.toDomain() = CityModel(
    name = name, latitude = latitude, longitude = longitude, country = country, state = state
)

fun DetailWeatherByCity.toDomain() = DetailWeatherModel(
    timezone = timezone,
    current = CurrentModel(
        temperature = current?.temperature,
        weather = current?.weather?.map { it.toDomain() }
    )
)

fun Weather.toDomain() = WeatherModel(
    main = main, description = description, icon = icon
)

fun SaveedCitiesEntity.toDomain() = SavedCitiesModel(
    nameCity = nameCity,
    country = country,
    state = state,
    temperature = temperature,
    mainWeather = mainWeather
)

fun SavedCitiesModel.toData() = SaveedCitiesEntity(
    nameCity = nameCity,
    country = country,
    state = state,
    temperature = temperature,
    mainWeather = mainWeather
)