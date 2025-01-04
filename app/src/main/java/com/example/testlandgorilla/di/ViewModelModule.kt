package com.example.testlandgorilla.di

import com.example.domain.repository.CitySearchRepository
import com.example.domain.usecase.GetDetailWeatherByCityUseCase
import com.example.domain.usecase.GetInsertCityDBUseCase
import com.example.testlandgorilla.ui.detail.DetailWeatherViewModel
import com.example.testlandgorilla.ui.savedcities.WeatherMainViewModel
import com.example.testlandgorilla.ui.searcher.SearchCitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchCitiesViewModel(get()) }
    viewModel { WeatherMainViewModel(get()) }
    viewModel { DetailWeatherViewModel(get<GetDetailWeatherByCityUseCase>(), get<GetInsertCityDBUseCase>()) }
}