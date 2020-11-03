package com.titou.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


data class Weather(

    val city: String?,
    val latitude: Float?,
    val longitude: Float?,
    val currentWeather: CurrentWeather?,
    val weatherForecast: List<DatedWeatherForecast> = emptyList(),
    val hourlyWeatherForecast: List<HourlyWeather> = emptyList()

)
