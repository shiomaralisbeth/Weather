package com.example.data.source.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savedCities_table")
data class SaveedCitiesEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "id")
    val id: Int = 0,
    @ColumnInfo(name= "nameCity")
    val nameCity: String,
    @ColumnInfo(name= "country")
    val country: String,
    @ColumnInfo(name= "state")
    val state: String,
    @ColumnInfo(name= "temperature")
    val temperature: String,
    @ColumnInfo(name= "mainWeather")
    val mainWeather: String,

)