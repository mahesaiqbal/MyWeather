package com.mahesaiqbal.myweather

import org.json.JSONObject
import java.text.DecimalFormat

class WeatherItems constructor(jsonObject: JSONObject) {

    var id: Int = 0
    var name: String? = null
    var currentWeather: String? = null
    var description: String? = null
    var temperature: String? = null

    init {
        try {
            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            val currentWeather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main")
            val description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description")
            val tempInKelvin = jsonObject.getJSONObject("main").getDouble("temp")

            val tempInCelsius = tempInKelvin - 273
            val temperature = DecimalFormat("##.##").format(tempInCelsius)
            this.id = id
            this.name = name
            this.currentWeather = currentWeather
            this.description = description
            this.temperature = temperature

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}