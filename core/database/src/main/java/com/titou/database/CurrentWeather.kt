package com.titou.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "current_weather")
data class CurrentWeather(
    @ColumnInfo(name = "observation_time")
    val observationTime: String,
    @ColumnInfo(name = "weather_descriptions")
    val weatherDescriptions: String,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: String,
    @ColumnInfo(name = "temperature")
    val temperature: Int
)
