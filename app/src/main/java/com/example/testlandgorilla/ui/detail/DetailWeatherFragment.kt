package com.example.testlandgorilla.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.DetailWeatherModel
import com.example.testlandgorilla.R
import com.example.testlandgorilla.databinding.FragmentDetailWeatherBinding
import com.example.testlandgorilla.utils.formatToKelvin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailWeatherFragment : Fragment() {
    private lateinit var binding: FragmentDetailWeatherBinding
    private val viewModel: DetailWeatherViewModel by viewModel()
    private lateinit var detailCity:DetailWeatherModel

    private val args by lazy {
        navArgs<DetailWeatherFragmentArgs>().value
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailWeatherBinding.inflate(inflater)
        viewModel.getDetailWeather(args.latitude.toDouble(), args.longitude.toDouble())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()
        initListeners()
    }

    private fun initObservables() {
        lifecycleScope.launch {
            viewModel.uiEvent.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
                .collectLatest {
                    it.ui.loading.let {
                        showLoader(true)
                    }
                    it.ui.result.let { detail ->
                        showLoader(false)
                        detail?.let { setUpDetailUI(detail) }
                    }
                }
        }
    }

    private fun setUpDetailUI(detail: DetailWeatherModel) = with(binding) {
        detailCity = detail
        detailWeatherCityTextView.text = args.city
        detailWeatherMainTextView.text = detail.current?.weather?.get(0)?.main
        detailWeatherTempetureTextView.text = detail.current?.temperature?.formatToKelvin()
        detailWeatherDescriptionTextView.text = detail.current?.weather?.get(0)?.description
    }

    private fun showLoader(isVisible: Boolean) = with(binding) {
        detailLoader.isVisible = isVisible
        viewDetail.isVisible = !isVisible
    }

    private fun initListeners() = with(binding) {
        addCityButton.setOnClickListener {
            viewModel.insertCity(
                nameCity = args.city,
                country = args.country,
                state = "",
                temperature = detailCity.current?.temperature.toString()?: "",
                mainWeather = binding.detailWeatherDescriptionTextView.text.toString()
            )
            findNavController().navigate(R.id.action_detailWeatherFragment_to_weatherMainFragment)
        }
    }
}