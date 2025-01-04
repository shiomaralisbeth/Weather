package com.example.testlandgorilla.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CityModel
import com.example.domain.model.SavedCitiesModel
import com.example.testlandgorilla.databinding.ItemCitySearchedBinding
import com.example.testlandgorilla.databinding.ItemSavedCitiesBinding
import com.example.testlandgorilla.utils.formatToKelvin

class SavedCitiesAdapter (private val onClickItem: (SavedCitiesModel) -> Unit) :
    ListAdapter<SavedCitiesModel, SavedCitiesAdapter.CustomViewHolder>(
        ProviderSearchCitiesAdapterDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder.from(parent, onClickItem)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CustomViewHolder(
        private val binding: ItemSavedCitiesBinding,
        private val onClickItem: (SavedCitiesModel) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cityModel: SavedCitiesModel) {
            binding.cityNameTextView.text = cityModel.nameCity
            binding.countryTextView.text = cityModel.country
            binding.mainTextView.text = cityModel.mainWeather
            binding.temperatureTextView.text = cityModel.temperature.formatToKelvin()
            binding.root.setOnClickListener {
                onClickItem(cityModel)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClickItem: (SavedCitiesModel) -> Unit
            ): CustomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return CustomViewHolder(
                    ItemSavedCitiesBinding.inflate(
                        layoutInflater, parent, false
                    ), onClickItem
                )
            }
        }

    }

    class ProviderSearchCitiesAdapterDiffUtil :
        DiffUtil.ItemCallback<SavedCitiesModel>() {
        override fun areItemsTheSame(
            oldItem: SavedCitiesModel,
            newItem: SavedCitiesModel
        ): Boolean {
            return oldItem.nameCity == newItem.nameCity
        }

        override fun areContentsTheSame(
            oldItem: SavedCitiesModel,
            newItem: SavedCitiesModel
        ): Boolean {
            return oldItem == newItem
        }

    }
}
