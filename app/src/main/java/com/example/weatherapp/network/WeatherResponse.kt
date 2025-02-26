package com.example.weatherapp.network


data class WeatherResponse(
    val main: Main, // Specify the main weather information
    val name: String, // Specify the name of the location
    val weather: List<Weather> // Specify the weather information
)
data class Main(
    val temp: Double // Specify the temperature
)

data class Weather(
    val description: String, // Specify the weather description
    val icon: String // Specify the weather icon
)
