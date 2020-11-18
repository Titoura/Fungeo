package com.titou.data_source.remote.weather_api.dtos

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    @SerializedName("daily")
    val dailyWeatherForecast: List<DailyWeatherResponseDto?>?,

    @SerializedName("hourly")
    val hourlyWeatherForecast: List<HourlyWeatherResponseDto?>?,

    @SerializedName("current")
    val currentWeather: CurrentWeatherResponseDto?,

    @SerializedName("lon")
    val longitude: Float?,

    @SerializedName("lat")
    val latitude: Float?


)