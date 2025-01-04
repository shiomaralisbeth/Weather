package com.example.data.repository

import com.example.data.mapper.toDomain
import com.example.data.source.datastore.DetailWeatherDataStore
import com.example.domain.model.DetailWeatherModel
import com.example.domain.repository.DetailWeatherRepository
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailWeatherRepositoryImpl(
    private val detailWeatherDataStore: DetailWeatherDataStore
): DetailWeatherRepository {
    override suspend fun getDetailWeatherByCity(
        latitude: Double,
        longitude: Double
    ): Flow<Either<Failure, DetailWeatherModel>> =
        detailWeatherDataStore.getDetailWeather(latitude, longitude).map {
            when (it) {
                is Either.Success -> {
                    Either.Success(it.success.toDomain())
                }
                is Either.Error -> {
                    Either.Error(it.error)
                }

                else ->{
                    Either.Error(Failure.None)
                }
            }
        }
}