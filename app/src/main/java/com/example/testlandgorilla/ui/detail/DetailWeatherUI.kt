package com.example.testlandgorilla.ui.detail

import com.example.domain.model.DetailWeatherModel
import com.example.domain.utils.Failure

data class DetailWeatherUI(
    val ui: UIDetailWeather = UIDetailWeather()
)

data class UIDetailWeather(
    val loading: Boolean? = null,
    val failure: Failure? = null,
    val result: DetailWeatherModel? = null,
)
