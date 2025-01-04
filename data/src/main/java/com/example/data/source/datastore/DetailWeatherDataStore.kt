package com.example.data.source.datastore

import com.example.data.response.DetailWeatherByCity
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

interface DetailWeatherDataStore {
    suspend fun getDetailWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Either<Failure, DetailWeatherByCity>>
}