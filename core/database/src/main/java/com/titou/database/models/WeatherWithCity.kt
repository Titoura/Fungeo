package com.titou.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_weathers")
data class WeatherWithCity(
    @Embedded
    @PrimaryKey
    val city: City,
    @Embedded
    val currentWeather: CurrentWeather,
    @Embedded
    val weatherForecast: List<DatedWeatherForecast>
)
