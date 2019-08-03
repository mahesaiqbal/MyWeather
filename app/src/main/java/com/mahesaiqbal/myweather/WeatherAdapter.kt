package com.mahesaiqbal.myweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.mahesaiqbal.myweather.WeatherAdapter.WeatherViewHolder
import kotlinx.android.synthetic.main.weather_items.view.*

class WeatherAdapter : Adapter<WeatherViewHolder>() {

    private var data: ArrayList<WeatherItems> = arrayListOf()

    fun setData(items: ArrayList<WeatherItems>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder
            = WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_items, parent, false))

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bindItem(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class WeatherViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bindItem(weatherItems: WeatherItems) {
            itemView.textKota.text = weatherItems.name
            itemView.textTemp.text = weatherItems.temperature
            itemView.textDesc.text = weatherItems.description
        }
    }
}