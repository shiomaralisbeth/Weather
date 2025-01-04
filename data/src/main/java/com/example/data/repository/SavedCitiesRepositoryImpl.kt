package com.example.data.repository

import com.example.data.mapper.toData
import com.example.data.mapper.toDomain
import com.example.data.source.database.dao.SavedCitiesDao
import com.example.data.source.database.entities.SaveedCitiesEntity
import com.example.domain.model.SavedCitiesModel
import com.example.domain.repository.SavedCitiesRepository

class SavedCitiesRepositoryImpl(
    private val savedCitiesDao: SavedCitiesDao
): SavedCitiesRepository {
    override suspend fun getAllSavedCities(): List<SavedCitiesModel> {
        val response: List<SaveedCitiesEntity> = savedCitiesDao.getAllCities()
        return response.map { it.toDomain() }
    }

    override suspend fun insertCity(savedCitiesModel: SavedCitiesModel) {
        return savedCitiesDao.insertCity(savedCitiesModel.toData())
    }
}