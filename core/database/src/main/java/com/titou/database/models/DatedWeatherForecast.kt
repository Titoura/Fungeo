package com.titou.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

@Entity(tableName = "dated_weather_forecast")
data class DatedWeatherForecast(

    val date: LocalDate,

    val temperature: TemperatureForecast?,

    val perceivedTemperature: PerceivedTemperatureForecast?,

    val weatherDescription: List<WeatherDescription>?
)
