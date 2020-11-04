package com.titou.database.models

import com.google.gson.annotations.SerializedName
import com.titou.database.R
import java.time.LocalDateTime

data class HourlyWeather(

    val dateTime: LocalDateTime?,
    val temperature: Float?,
    val weatherDescriptions: List<WeatherDescription?>?,
    val feelsLike: Float?,


    val celsiusTemperature: Int? = temperature?.minus(273.15f)?.toInt()


) {
    fun getIconRes(): Int {
        return with(weatherDescriptions?.first()?.main) {
            when {
                this == null -> R.drawable.ic_cloud
                this.contains("Clear") -> R.drawable.ic_sun
                this.contains("Clouds") -> R.drawable.ic_cloud
                this.contains("Snow") -> R.drawable.ic_snow
                this.contains("Rain") -> R.drawable.ic_rain
                this.contains("Thunderstorm") -> R.drawable.ic_thunderstorm
                else -> R.drawable.ic_cloud
            }
        }
    }

}
