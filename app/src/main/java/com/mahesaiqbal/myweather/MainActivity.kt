package com.mahesaiqbal.myweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var weatherAdapter: WeatherAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getWeathers().observe(this, getWeather)

        weatherAdapter = WeatherAdapter()
        weatherAdapter.notifyDataSetChanged()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = weatherAdapter
        }

        btnCity.setOnClickListener {
            val city = editCity.text.toString()

            if (!TextUtils.isEmpty(city)) {
                viewModel.setWeather(city)
            }

            showLoading(true)
        }
    }

    private val getWeather = Observer<ArrayList<WeatherItems>> { weatherItems ->
            if (weatherItems != null) {
                weatherAdapter.setData(weatherItems)
                showLoading(false)
            }
        }

    fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}
