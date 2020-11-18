package com.fungeo.presentation

import com.fungeo.data.entity.CurrentWeather
import com.fungeo.data.entity.DatedWeatherForecast
import com.fungeo.data.entity.HourlyWeather
import com.fungeo.data.entity.TemperatureForecast
import java.time.LocalDateTime
import java.time.ZoneId

//TODO: Create Utils module to access everywhere
//TODO: merge getIconRes functions

fun Float.toCelsius() : Int = (this-273.15f).toInt()

fun CurrentWeather.getIconRes(): Int {
    val now = LocalDateTime.now(ZoneId.systemDefault())
    return with(weatherDescriptions.first().main) {
        when {
            this == null -> R.drawable.ic_cloud
            this.contains("Clear") && now.hour in 8..18 -> R.drawable.ic_sun
            this.contains("Clear") -> R.drawable.ic_moon
            this.contains("Clouds") -> R.drawable.ic_cloud
            this.contains("Snow") -> R.drawable.ic_snow
            this.contains("Rain") -> R.drawable.ic_rain
            this.contains("Thunderstorm") -> R.drawable.ic_thunderstorm
            else -> R.drawable.ic_cloud
        }
    }
}

fun HourlyWeather.getIconRes(): Int {
    val now = LocalDateTime.now(ZoneId.systemDefault())
    return with(weatherDescriptions?.first()?.main) {
        when {
            this == null -> R.drawable.ic_cloud
            this.contains("Clear") && now.hour in 8..18 -> R.drawable.ic_sun
            this.contains("Clear") -> R.drawable.ic_moon
            this.contains("Clouds") -> R.drawable.ic_cloud
            this.contains("Snow") -> R.drawable.ic_snow
            this.contains("Rain") -> R.drawable.ic_rain
            this.contains("Thunderstorm") -> R.drawable.ic_thunderstorm
            else -> R.drawable.ic_cloud
        }
    }
}

fun DatedWeatherForecast.getIconRes(): Int {
    val now = LocalDateTime.now(ZoneId.systemDefault())
    return with(weatherDescriptions?.first()?.main) {
        when {
            this == null -> R.drawable.ic_cloud
            this.contains("Clear") && now.hour in 8..18 -> R.drawable.ic_sun
            this.contains("Clear") -> R.drawable.ic_moon
            this.contains("Clouds") -> R.drawable.ic_cloud
            this.contains("Snow") -> R.drawable.ic_snow
            this.contains("Rain") -> R.drawable.ic_rain
            this.contains("Thunderstorm") -> R.drawable.ic_thunderstorm
            else -> R.drawable.ic_cloud
        }
    }
}
