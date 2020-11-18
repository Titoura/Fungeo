package com.fungeo.data.entity

import java.time.LocalDateTime

data class CurrentWeather(
    val dateTime: LocalDateTime,
    val weatherDescriptions: List<WeatherDescription> = emptyList(),
    val temperature: Float,
    val perceivedTemperature: Float
)