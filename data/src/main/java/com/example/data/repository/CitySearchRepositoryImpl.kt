package com.example.data.repository

import android.util.Log
import com.example.data.mapper.toDomain
import com.example.data.source.datastore.CitySearchDataStore
import com.example.domain.model.CityModel
import com.example.domain.repository.CitySearchRepository
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CitySearchRepositoryImpl(
    private val citySearchDataStore: CitySearchDataStore
) : CitySearchRepository {

    override suspend fun getCitySearch(
        cityName: String,
        limitResults: Int?
    ): Flow<Either<Failure, List<CityModel>>> =
        citySearchDataStore.getCitySearch(cityName = cityName, limit = limitResults).map {
            when (it) {
                is Either.Success -> {
                    Either.Success(it.success.map { it.toDomain() })
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