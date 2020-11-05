package com.titou.urgo.locations

import android.util.Log
import com.titou.database.models.*
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.merge
import org.koin.core.KoinComponent
import java.time.LocalDate
import java.time.LocalDateTime

internal class LocationsPresenter(
    private val interactor: LocationsInteractor
) : KoinComponent {

    private val TAG = "HomePresenter"

    fun props(): Observable<Props> {

        return interactor.getLocations().flatMap { locationsWithName ->
            locationsWithName.map { locWithName ->
                interactor.fetchWeatherFromServer(
                    locWithName.location
                        ?: throw IllegalStateException("Location should not be null")
                )
                    .map { weather ->
                        LocationWithNameAndWeather(
                            weather = weather,
                            locationWithName = locWithName
                        )
                    }

            }.merge().toList().flatMapMaybe { Maybe.just(Props(it, false)) }.toObservable()
        }
    }


    fun handleOnTextValidated(locationName: String) =
        interactor.insertLocations(interactor.searchLocationForName(locationName, false))
            .subscribe({ }, {
                Log.e("LocationsPresenter", "Could not save the location")
            })


    fun handleOnRemoveClick(locationWithName: LocationWithName) =
        interactor.removeLocation(locationWithName).subscribe({},{
            Log.e("LocationsPresenter", "Failed removing location : ${locationWithName.name}")
        })

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
}


