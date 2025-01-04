package com.example.domain.repository

import com.example.domain.model.CityModel
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

interface CitySearchRepository {

    suspend fun getCitySearch(cityName: String, limitResults: Int?): Flow<Either<Failure, List<CityModel>>>
}