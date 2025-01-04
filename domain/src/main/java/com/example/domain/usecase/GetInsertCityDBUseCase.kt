package com.example.domain.usecase

import com.example.domain.model.SavedCitiesModel
import com.example.domain.repository.SavedCitiesRepository

class GetInsertCityDBUseCase(private val repository: SavedCitiesRepository) {
    suspend operator fun invoke(citiesModel: SavedCitiesModel) {
        return repository.insertCity(citiesModel)
    }
}