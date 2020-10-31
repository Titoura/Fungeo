package com.titou.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.time.LocalDate

@Entity(tableName = "weather_forecast")
data class DatedWeatherForecast(
    @ColumnInfo(name = "date")
    val name: LocalDate,
    @ColumnInfo(name = "min_temp")
    val minTemp: Int,
    @ColumnInfo(name = "max_temp")
    val maxTemp: Int,
    @ColumnInfo(name = "avg_temp")
    val avgTemp: Int,
    @ColumnInfo(name = "min_temp")
    val country: String,
)
