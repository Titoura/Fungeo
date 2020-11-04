package com.titou.database.models

import androidx.room.Entity
import com.titou.database.R
import java.time.LocalDate

@Entity(tableName = "dated_weather_forecast")
data class DatedWeatherForecast(

    val date: LocalDate,

    val temperature: TemperatureForecast?,

    val perceivedTemperature: PerceivedTemperatureForecast?,

    val weatherDescriptions: List<WeatherDescription>?
)
{
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
