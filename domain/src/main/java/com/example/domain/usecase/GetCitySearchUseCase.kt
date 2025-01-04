package com.example.domain.usecase

import android.util.Log
import com.example.domain.model.CityModel
import com.example.domain.repository.CitySearchRepository
import com.example.domain.utils.Either
import com.example.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

class GetCitySearchUseCase(private val repository: CitySearchRepository) {
    suspend operator fun invoke(
        cityName: String,
        limitResults: Int?
    ): Flow<Either<Failure, List<CityModel>>> {
        return repository.getCitySearch(cityName = cityName, limitResults = limitResults)
    }
}