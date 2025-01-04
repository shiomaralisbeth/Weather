package com.example.data.utils

import android.app.Application
import androidx.room.Room
import com.example.data.source.database.SavedCitiesDataBase
import com.example.data.source.database.dao.SavedCitiesDao
import com.example.data.utils.Constants.Companion.DATA_BASE_NAME

object DataBaseUtils {


    fun provideLocalDataBase(
        applicationContext: Application
    ): SavedCitiesDataBase {
        return Room.databaseBuilder(
            applicationContext,
            SavedCitiesDataBase::class.java,
            DATA_BASE_NAME
        )
            .build()
    }

    fun provideSavedCitiesDao(mistiDataBase: SavedCitiesDataBase): SavedCitiesDao {
        return mistiDataBase.getSavedCitiesDao()
    }
}