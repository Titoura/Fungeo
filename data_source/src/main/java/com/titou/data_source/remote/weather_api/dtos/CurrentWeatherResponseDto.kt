package com.titou.data_source.remote.weather_api.dtos

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponseDto(
    @SerializedName("temp")
    val temperature: Float?,
    @SerializedName("weather")
    val weatherDescriptions: List<WeatherDescriptionResponseDto?>?,
    @SerializedName("feels_like")
    val feelsLike: Float?
)
