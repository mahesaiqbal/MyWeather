package com.mahesaiqbal.myweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.AsyncHttpClient
import cz.msebera.android.httpclient.Header

class MainViewModel : ViewModel() {

    companion object {
        val API_KEY = "dd522c8235e5b956c6637e8a72a46189"
    }

    var listWeathers: MutableLiveData<ArrayList<WeatherItems>> = MutableLiveData()

    fun setWeather(cities: String) {
        val client = AsyncHttpClient()
        val listItems: ArrayList<WeatherItems> = arrayListOf()
        val url = "https://api.openweathermap.org/data/2.5/group?id=$cities&units=metric&appid=$API_KEY"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("list")
                    for (i in 0 until list.length()) {
                        val weather = list.getJSONObject(i)
                        val weatherItems = WeatherItems(weather)
                        listItems.add(weatherItems)
                    }

                    listWeathers.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message)
                }

            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                Log.d("onFailure", error.message)
            }
        })
    }

    fun getWeathers(): LiveData<ArrayList<WeatherItems>> = listWeathers
}