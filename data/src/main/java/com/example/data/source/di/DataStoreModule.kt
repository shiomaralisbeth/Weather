package com.example.data.source.di

import com.example.data.source.datastore.CitySearchDataStore
import com.example.data.source.datastore.DetailWeatherDataStore
import com.example.data.source.service.CitySearchDataStoreImpl
import com.example.data.source.service.DetailWeatherDataStoreImpl
import org.koin.dsl.module


val dataStoreModule = module {
    single<CitySearchDataStore> { CitySearchDataStoreImpl(get(), get()) }
    single<DetailWeatherDataStore> { DetailWeatherDataStoreImpl(get(), get()) }

}



