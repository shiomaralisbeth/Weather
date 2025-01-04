package com.example.data.source.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.source.database.entities.SaveedCitiesEntity

@Dao
interface SavedCitiesDao {
    @Query("SELECT * FROM savedCities_table")
    suspend fun getAllCities(): List<SaveedCitiesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: SaveedCitiesEntity)
}