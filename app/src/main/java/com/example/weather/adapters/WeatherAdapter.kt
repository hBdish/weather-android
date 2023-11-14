package com.example.weather.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.ListItemBinding

class WeatherAdapter : ListAdapter<WeatherModel, WeatherAdapter.Holder>(Comparator()) {
  class Holder(view: View) : RecyclerView.ViewHolder(view) {
    var binding = ListItemBinding.bind(view)

    fun bind(item: WeatherModel) = with(binding) {
      tvDateList.text = item.time
      tvConditionList.text = item.condition
      tvTempList.text = item.currentTemp
    }
  }

  class Comparator : DiffUtil.ItemCallback<WeatherModel>() {
    override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
      return oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    return Holder(view)
  }

  override fun onBindViewHolder(holder: Holder, position: Int) {
    holder.bind(getItem(position))
  }
}
