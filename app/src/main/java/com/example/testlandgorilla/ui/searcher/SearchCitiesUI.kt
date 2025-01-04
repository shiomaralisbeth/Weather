package com.example.testlandgorilla.ui.searcher

import com.example.domain.model.CityModel
import com.example.domain.utils.Failure

data class SearchCitiesUI(
    val ui: UISearchCities = UISearchCities()
)

data class UISearchCities(
    val loading: Boolean? = null,
    val failure: Failure? = null,
    val result: List<CityModel>? = null,
)
