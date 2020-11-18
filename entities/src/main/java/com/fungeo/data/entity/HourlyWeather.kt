package com.fungeo.data.entity

import java.time.LocalDateTime


data class HourlyWeather(

    val dateTime: LocalDateTime?,
    val temperature: Float?,
    val weatherDescriptions: List<WeatherDescription?>?,
    val feelsLike: Float?,


    val celsiusTemperature: Int? = temperature?.minus(273.15f)?.toInt()


)
