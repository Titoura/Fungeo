package com.fungeo.data.entity

import java.time.LocalDate

data class DatedWeatherForecast(

    val date: LocalDate,

    val temperature: TemperatureForecast?,

    val perceivedTemperature: PerceivedTemperatureForecast?,

    val weatherDescriptions: List<WeatherDescription>?
)
