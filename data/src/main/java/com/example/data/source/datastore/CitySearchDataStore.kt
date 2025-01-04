package com.example.data.source.datastore

import com.example.data.response.CityResponse
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

interface CitySearchDataStore {
    suspend fun getCitySearch(cityName: String, limit: Int?= null): Flow<Either<Failure, List<CityResponse>>>
}