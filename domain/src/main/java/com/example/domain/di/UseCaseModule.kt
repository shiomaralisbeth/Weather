package com.example.domain.di

import com.example.domain.usecase.GetCitySearchUseCase
import com.example.domain.usecase.GetDetailWeatherByCityUseCase
import com.example.domain.usecase.GetInsertCityDBUseCase
import com.example.domain.usecase.GetSavedCitiesDBUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetCitySearchUseCase(get()) }
    factory { GetDetailWeatherByCityUseCase(get()) }
    factory { GetSavedCitiesDBUseCase(get()) }
    factory { GetInsertCityDBUseCase(get()) }
}