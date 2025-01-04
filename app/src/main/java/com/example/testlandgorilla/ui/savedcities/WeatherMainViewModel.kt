package com.example.testlandgorilla.ui.savedcities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.SavedCitiesModel
import com.example.domain.usecase.GetInsertCityDBUseCase
import com.example.domain.usecase.GetSavedCitiesDBUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherMainViewModel(
    private val getSavedCitiesDBUseCase: GetSavedCitiesDBUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _listSavedCities =
        MutableLiveData<List<SavedCitiesModel>>()
    val listSavedCities: LiveData<List<SavedCitiesModel>>
        get() = _listSavedCities

    fun getAllSavedCities(){
        viewModelScope.launch{
            val result= withContext(dispatcher){
                getSavedCitiesDBUseCase.invoke()
            }
            _listSavedCities.value = result
        }
    }
}