package com.titou.database.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class HourlyWeather(

    val dateTime: LocalDateTime?,
    val temperature: Float?,
    val weatherDescriptions: List<WeatherDescription?>?,
    val feelsLike: Float?,


    val celsiusTemperature : Int? = temperature?.minus(273.15f)?.toInt()

)
