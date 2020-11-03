package com.titou.fungeo.weather.cqrs.dtos

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class DailyWeatherResponseDto(
    @SerializedName("dt")
    val dateTime: BigInteger?,
    @SerializedName("temp")
    val temp: TemperatureForecastResponseDto?,
    @SerializedName("feels_like")
    val perceivedTemperature: PerceivedTemperatureForecastResponseDto?,
    @SerializedName("weather")
    val weatherDescription: List<WeatherDescriptionResponseDto?>
)
