package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var cityName = mutableStateOf("")
    var weatherInfo = mutableStateOf("Enter a city and press search.")
    private val apiKey = "44c1f81da55eda6af9ab0d0cbd2bfe31"

    fun updateCity(newCity: String) {
        if (newCity.isEmpty()){
            weatherInfo.value = "Please enter city name"
        } else if (!newCity.matches(Regex("^[a-zA-Z]*$"))) {
            weatherInfo.value = "Please enter a valid city name"
        }
        else {
            cityName.value = newCity
        }
    }

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.weatherApiService.getWeather(cityName.value, apiKey)
                Log.d("WeatherViewModel", "API Response: $response") // Print response in Logcat
                weatherInfo.value =
                    "Weather in ${cityName.value}: ${response.main.temp}°C, ${response.weather[0].description} ${response.weather[0].icon}"
            } catch (e: Exception) {
                weatherInfo.value = "Error: Could not fetch weather data."
                Log.e("WeatherViewModel", "API Request Failed: ${e.message}")
            }
        }
    }
}