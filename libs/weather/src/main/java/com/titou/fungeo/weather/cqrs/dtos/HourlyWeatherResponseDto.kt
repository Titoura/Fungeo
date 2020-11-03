package com.titou.fungeo.weather.cqrs.dtos

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class HourlyWeatherResponseDto(
    @SerializedName("dt")
    val dateTime: Int,
    @SerializedName("temp")
    val temperature: Float?,
    @SerializedName("weather")
    val weatherDescriptions: List<WeatherDescriptionResponseDto?>?,
    @SerializedName("feels_like")
    val feelsLike: Float?
)
