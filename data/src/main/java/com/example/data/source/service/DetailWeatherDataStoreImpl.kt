package com.example.data.source.service

import com.example.data.base.CallServiceBase
import com.example.data.response.DetailWeatherByCity
import com.example.data.source.RestService
import com.example.data.source.datastore.DetailWeatherDataStore
import com.example.data.utils.ConnectionUtils
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DetailWeatherDataStoreImpl(
    private val restService: RestService,
    private val connectionUtils: ConnectionUtils
) : DetailWeatherDataStore, CallServiceBase() {
    override suspend fun getDetailWeather(
        latitude: Double,
        longitude: Double
    ): Flow<Either<Failure, DetailWeatherByCity>> = flow {
        val result = callService {
            restService.getDetailWeatherByCity(latitude, longitude)
        }
        emit(result)
    }

    override fun getNetworkUtils(): ConnectionUtils = connectionUtils
}