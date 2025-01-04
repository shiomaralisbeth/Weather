package com.example.domain.usecase

import com.example.domain.model.SavedCitiesModel
import com.example.domain.repository.SavedCitiesRepository

class GetSavedCitiesDBUseCase(private val repository: SavedCitiesRepository) {
    suspend operator fun invoke(): List<SavedCitiesModel> {
        return repository.getAllSavedCities()
    }
}