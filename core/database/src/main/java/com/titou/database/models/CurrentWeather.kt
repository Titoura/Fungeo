package com.titou.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.titou.database.R
import java.time.LocalDateTime

data class CurrentWeather(
    val dateTime: LocalDateTime,
    val weatherDescriptions: List<WeatherDescription> = emptyList(),
    val temperature: Float,
    val perceivedTemperature: Float
){

    //TODO: Add night icons
    fun getIconRes(): Int {
        return with(weatherDescriptions.first().main) {
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
