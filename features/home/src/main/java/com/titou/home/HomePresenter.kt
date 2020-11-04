package com.titou.home

import android.location.Location
import com.titou.database.models.*
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent
import java.time.LocalDate
import java.time.LocalDateTime

internal class HomePresenter(
    private val interactor: HomeInteractor
) : KoinComponent {

    private val TAG = "HomePresenter"

    fun props(): Observable<Props> {

        return interactor.getLastLocation().flatMap { currentLocation ->
            currentLocation.let {
                interactor.fetchWeatherFromServer(it)
                    .map {
                        currentLocation?.let { location : Location ->
                            Props(weather = it, location = location, locationName = interactor.getCurrentLocationName(location)?:"Your weather")
                        }
                    }
            }
        }
    }
}


// FIXME: remove temporary mock, used for dev only
// TODO: reuse in unit tests
fun mockProps(): Weather {
    val longitude = 2.3488f
    val latitude = 48.8534f
    val currentWeather = CurrentWeather(
        LocalDateTime.now(),
        listOf(WeatherDescription("Sunny", "Sunny")),
        30f,
        15f
    )
    val weatherForecast = listOf(
        DatedWeatherForecast(
            LocalDate.now(),
            TemperatureForecast(15f, 15f, 15f, 15f),
            PerceivedTemperatureForecast(15f, 15f),
            listOf(WeatherDescription("Sunny", "Sunny"))
        )
    )

    return Weather(
        city = "Paris",
        latitude = latitude,
        longitude = longitude,
        currentWeather = currentWeather,
        weatherForecast = weatherForecast
    )
}


