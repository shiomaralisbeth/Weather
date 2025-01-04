package com.example.testlandgorilla.ui.searcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.CityModel
import com.example.testlandgorilla.databinding.FragmentSearchCitiesBinding
import com.example.testlandgorilla.ui.adapters.SearchCitiesAdapter
import com.example.testlandgorilla.utils.showKeyboard
import com.example.testlandgorilla.utils.textChanges
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchCitiesFragment : Fragment() {
    private lateinit var binding: FragmentSearchCitiesBinding
    private val searchCitiesViewModel: SearchCitiesViewModel by viewModel()
    private val adapterSearchCities by lazy {
        SearchCitiesAdapter(::onClickSearchCity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        initObservables()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCitiesBinding.inflate(inflater)
        return binding.root
    }

    private fun setUpUI() = with(binding) {
        openKeyBoard()
        initAdapter()
        initSearcher()
    }

    private fun initObservables() {
        lifecycleScope.launch {
            searchCitiesViewModel.uiEvent.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collectLatest {
                    it.ui.result.let { listCities ->
                        if (listCities?.isNotEmpty() == true) {
                            adapterSearchCities.updateList(listCities)
                            adapterSearchCities.submitList(listCities)
                        }
                    }
                }
        }
    }

    private fun initSearcher() {
        lifecycleScope.launch {
            binding.widgetSearcherInclude.searchCitiesEditText.textChanges()
                .debounce(1000) // 1 segundo de espera después de que el usuario deja de escribir
                .mapLatest { it.toString() }
                .collectLatest { query ->
                    searchCitiesViewModel.getCities(query)

                }
        }

    }

    private fun openKeyBoard() {
        binding.widgetSearcherInclude.searchCitiesEditText.requestFocus()
        binding.widgetSearcherInclude.searchCitiesEditText.showKeyboard()
    }

    private fun initAdapter() {
        binding.cityListRecyclerView.adapter = adapterSearchCities
    }

    private fun onClickSearchCity(model: CityModel) {
        goToDetailWeather(model)
    }

    private fun goToDetailWeather(model: CityModel) {
        findNavController().navigate(
            SearchCitiesFragmentDirections.actionSearchCitiesFragmentToDetailWeatherFragment(
                model.name?:"",
                model.country?:"",
                model.latitude.toString(),
                model.longitude.toString()
            )
        )
    }
}