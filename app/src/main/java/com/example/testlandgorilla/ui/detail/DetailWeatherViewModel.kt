package com.example.testlandgorilla.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SavedCitiesModel
import com.example.domain.usecase.GetDetailWeatherByCityUseCase
import com.example.domain.usecase.GetInsertCityDBUseCase
import com.example.domain.utils.Either.Error
import com.example.domain.utils.Either.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailWeatherViewModel(
    private val getDetailWeatherByCityUseCase: GetDetailWeatherByCityUseCase,
    private val getInsertCityDBUseCase: GetInsertCityDBUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _UIEvent = MutableStateFlow(DetailWeatherUI())
    val uiEvent = _UIEvent.asStateFlow()

    fun getDetailWeather(latitude: Double, longitude: Double) {
        viewModelScope.launch(dispatcher) {
            _UIEvent.update {
                it.copy(
                    ui = UIDetailWeather(
                        loading = true
                    )
                )
            }
            getDetailWeatherByCityUseCase.invoke(latitude, longitude = longitude)
                .onEach { response ->
                    when (response) {
                        is Success -> {
                            _UIEvent.update {
                                it.copy(
                                    ui = UIDetailWeather(
                                        loading = false,
                                        result = response.success
                                    )
                                )
                            }
                        }

                        is Error -> {
                            _UIEvent.update {
                                it.copy(
                                    ui = UIDetailWeather(
                                        loading = false,
                                        failure = response.error
                                    )
                                )
                            }
                        }
                    }
                }.launchIn(this)
        }
    }

    fun insertCity(
        nameCity: String,
        country: String,
        state: String,
        temperature: String,
        mainWeather: String
    ) {
        viewModelScope.launch(dispatcher) {
            getInsertCityDBUseCase.invoke(
                SavedCitiesModel(
                    nameCity = nameCity,
                    country = country,
                    state = state,
                    temperature = temperature,
                    mainWeather = mainWeather
                )
            )
        }
    }
}