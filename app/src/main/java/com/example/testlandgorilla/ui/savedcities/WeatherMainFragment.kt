package com.example.testlandgorilla.ui.savedcities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.domain.model.SavedCitiesModel
import com.example.testlandgorilla.R
import com.example.testlandgorilla.databinding.FragmentMainBinding
import com.example.testlandgorilla.ui.adapters.SavedCitiesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherMainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: WeatherMainViewModel by viewModel()
    private val adapterSavedCities by lazy {
        SavedCitiesAdapter(::onClickSaved)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getAllSavedCities()
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        initListeners()
        initObservers()
    }

    private fun setUpUI() = with(binding) {
        widgetSearcherInclude.searchCitiesEditText.isFocusable = false
    }

    private fun initListeners() = with(binding) {
        widgetSearcherInclude.searchCitiesEditText.setOnClickListener {
            findNavController().navigate(R.id.action_weatherMainFragment_to_searchCitiesFragment)
        }
    }

    private fun initObservers() {
        viewModel.listSavedCities.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                adapterSavedCities.submitList(it)
                binding.cityListRecyclerView.adapter = adapterSavedCities
            } else{
                //ToDo: View de listado vacio "Agregar ciudad"
            }
        })
    }

    private fun onClickSaved(model: SavedCitiesModel) {
    }
}