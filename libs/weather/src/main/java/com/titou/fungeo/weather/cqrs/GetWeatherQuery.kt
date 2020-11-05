package com.titou.fungeo.weather.cqrs;

import android.location.Location
import com.titou.database.models.*
import com.titou.fungeo.weather.cqrs.api.WeatherApiService
import com.titou.fungeo.weather.serializers.toWeatherModel
//import com.titou.fungeo.weather.serializers.toWeatherModel
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent
import java.time.LocalDate
import java.time.LocalDateTime


class GetWeatherQuery(
    private val api : WeatherApiService
) : KoinComponent {

    //FIXME: extract api key
    private val API_KEY = "32349591b103bde8f9faa1809035c44e"

    fun fetchWeatherFromServerAndSave(location: Location): Observable<Weather> {
        return api.getWeatherForLocation(
            longitude = location.longitude.toFloat(),
            latitude = location.latitude.toFloat(),
            api_key = API_KEY,
            exclusions = ""
        )
            .map {
                it.body()
                    ?: throw IllegalStateException("Error parsing FetchWeather() JSON body")
            }
            .map{
                it.toWeatherModel()
            }
    }

    // TODO: remove temporary mock, used for dev only
    // TODO: reuse in unit tests
    fun mockProps(): Weather {
        val longitude = 2.3488f
        val latitude = 48.8534f
        val city = "Paris"
        val currentWeather = CurrentWeather(LocalDateTime.now(), listOf(WeatherDescription("Sunny", "Sunny")), 30f, 15f)
        val weatherForecast = listOf(
            DatedWeatherForecast(
                LocalDate.now(), TemperatureForecast(15f, 15f, 15f, 15f), PerceivedTemperatureForecast(15f, 15f), listOf(
                    WeatherDescription("Sunny", "Sunny")
                ))
        )

        return Weather(
            city = city,
            latitude = latitude,
            longitude = longitude,
            currentWeather = currentWeather,
            weatherForecast = weatherForecast
        )
    }

}

