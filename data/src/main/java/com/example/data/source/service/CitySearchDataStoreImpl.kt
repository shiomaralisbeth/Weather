package com.example.data.source.service

import com.example.data.base.CallServiceBase
import com.example.data.response.CityResponse
import com.example.data.source.RestService
import com.example.data.source.datastore.CitySearchDataStore
import com.example.data.utils.ConnectionUtils
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CitySearchDataStoreImpl(
    private val restService: RestService,
    private val connectionUtils: ConnectionUtils
) : CitySearchDataStore, CallServiceBase() {

    override suspend fun getCitySearch(
        cityName: String,
        limit: Int?
    ): Flow<Either<Failure, List<CityResponse>>> = flow {

        val result = callService {
            restService.getCitiesSearch(cityName, limit)
        }
        emit(result)
    }

    override fun getNetworkUtils(): ConnectionUtils = connectionUtils
}