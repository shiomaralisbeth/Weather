package com.example.domain.usecase

import com.example.domain.model.DetailWeatherModel
import com.example.domain.repository.DetailWeatherRepository
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

class GetDetailWeatherByCityUseCase(private val repository: DetailWeatherRepository) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Flow<Either<Failure, DetailWeatherModel>>{
        return repository.getDetailWeatherByCity(latitude, longitude)
    }
}