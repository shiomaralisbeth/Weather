package com.example.domain.repository

import com.example.domain.model.DetailWeatherModel
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

interface DetailWeatherRepository {
    suspend fun getDetailWeatherByCity(
        latitude: Double,
        longitude: Double
    ): Flow<Either<Failure, DetailWeatherModel>>
}