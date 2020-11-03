package com.titou.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

data class CurrentWeather(
    val dateTime: LocalDateTime,
    val weatherDescriptions: List<WeatherDescription> = emptyList(),
    val temperature: Float,
    val perceivedTemperature: Float
)
