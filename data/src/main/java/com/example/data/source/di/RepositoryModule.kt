package com.example.data.source.di

import com.example.data.repository.CitySearchRepositoryImpl
import com.example.data.repository.DetailWeatherRepositoryImpl
import com.example.data.repository.SavedCitiesRepositoryImpl
import com.example.domain.repository.CitySearchRepository
import com.example.domain.repository.DetailWeatherRepository
import com.example.domain.repository.SavedCitiesRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<CitySearchRepository> { CitySearchRepositoryImpl(get()) }
    single<DetailWeatherRepository> { DetailWeatherRepositoryImpl(get()) }
    single<SavedCitiesRepository> { SavedCitiesRepositoryImpl(get()) }
}



