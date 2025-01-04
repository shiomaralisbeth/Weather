package com.example.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.source.database.dao.SavedCitiesDao
import com.example.data.source.database.entities.SaveedCitiesEntity

@Database(entities = [SaveedCitiesEntity::class], version = 1)
abstract class SavedCitiesDataBase: RoomDatabase() {
    abstract fun getSavedCitiesDao(): SavedCitiesDao
}