package com.example.domain.repository

import com.example.domain.model.SavedCitiesModel

interface SavedCitiesRepository {
    suspend fun getAllSavedCities(): List<SavedCitiesModel>
    suspend fun insertCity(savedCitiesModel: SavedCitiesModel)
}