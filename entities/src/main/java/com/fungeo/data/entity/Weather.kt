package com.fungeo.data.entity


data class Weather(

    val city: String?,
    val latitude: Float?,
    val longitude: Float?,
    val currentWeather: CurrentWeather?,
    val weatherForecast: List<DatedWeatherForecast> = emptyList(),
    val hourlyWeatherForecast: List<HourlyWeather> = emptyList()

)
