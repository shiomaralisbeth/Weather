package com.example.testlandgorilla.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CityModel
import com.example.testlandgorilla.databinding.ItemCitySearchedBinding

class SearchCitiesAdapter (private val onClickItem: (CityModel) -> Unit) :
    ListAdapter<CityModel, SearchCitiesAdapter.CustomViewHolder>(
        ProviderSearchCitiesAdapterDiffUtil()
    ) {

    var cities: List<CityModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder.from(parent, onClickItem)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val city = cities[position]
        holder.bind(city)
    }

    class CustomViewHolder(
        private val binding: ItemCitySearchedBinding,
        private val onClickItem: (CityModel) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cityModel: CityModel) {
            binding.cityNameResultTextView.text = "${cityModel.name}, ${cityModel.state}"
            binding.root.setOnClickListener {
                onClickItem(cityModel)
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                onClickItem: (CityModel) -> Unit
            ): CustomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                return CustomViewHolder(
                    ItemCitySearchedBinding.inflate(
                        layoutInflater, parent, false
                    ), onClickItem
                )
            }
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(citiesList: List<CityModel>) {
        this.cities = citiesList
        notifyDataSetChanged()
    }

    class ProviderSearchCitiesAdapterDiffUtil :
        DiffUtil.ItemCallback<CityModel>() {
        override fun areItemsTheSame(
            oldItem: CityModel,
            newItem: CityModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: CityModel,
            newItem: CityModel
        ): Boolean {
            return oldItem == newItem
        }

    }
}