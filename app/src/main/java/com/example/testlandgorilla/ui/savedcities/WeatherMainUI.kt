package com.example.testlandgorilla.ui.savedcities

import com.example.domain.model.SavedCitiesModel
import com.example.domain.utils.Failure

data class WeatherMainUI(
    val ui: UIWeatherMain = UIWeatherMain()
)

data class UIWeatherMain(
    val loading: Boolean? = null,
    val failure: Failure? = null,
    val result: List<SavedCitiesModel>? = null,
)