package com.example.testlandgorilla.ui.searcher

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCitySearchUseCase
import com.example.domain.utils.Either
import com.example.domain.utils.Either.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchCitiesViewModel(
    private val getCitySearchUseCase: GetCitySearchUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _UIEvent = MutableStateFlow(SearchCitiesUI())
    val uiEvent = _UIEvent.asStateFlow()
    var job: Job? = null

    fun getCities(param: String) {

        job = viewModelScope.launch(dispatcher) {
            _UIEvent.update {
                it.copy(
                    ui = UISearchCities(
                        loading = true
                    )
                )
            }
            getCitySearchUseCase.invoke(param, 10).onEach { search ->
                when (search) {
                    is Success -> {
                        _UIEvent.update {
                            it.copy(
                                ui = UISearchCities(
                                    loading = false,
                                    result = search.success
                                )
                            )
                        }
                    }

                    is Error -> {
                        _UIEvent.update {
                            it.copy(
                                ui = UISearchCities(
                                    loading = false,
                                    failure = search.error
                                )
                            )
                        }
                    }
                }
            }.launchIn(this)

        }
    }


    private fun cancelSearch() {
        job?.cancel()
    }
}