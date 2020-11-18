package com.fungeo.presentation.locations

import android.util.Log
import com.fungeo.data.entity.LocationWithName
import com.titou.use_cases.LocationRepository
import com.titou.use_cases.WeatherRepository
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.merge
import org.koin.core.KoinComponent

internal class LocationsPresenter(
    private val locationRepository: LocationRepository,
    private val weatherRepository: WeatherRepository
) : KoinComponent {

    private val TAG = "LocationsPresenter"

    fun props(): Observable<Props> {

        return locationRepository.getAllPersistedLocations().flatMap { locationsWithName ->
            locationsWithName.map { locWithName ->
                weatherRepository.getWeatherForLocation(locWithName)
                    .map { weather ->
                        LocationWithNameAndWeather(
                            weather = weather,
                            locationWithName = locWithName
                        )
                    }

            }.merge().toList().flatMapMaybe { Maybe.just(Props(it, false)) }.toObservable()
        }
    }


    fun handleOnTextValidated(locationName: String) = locationRepository.saveNewLocation(locationName)
                ?.subscribe({
                            Log.i(TAG, "Saved a new location successfully")
                }, {
                    Log.w("LocationsPresenter", "Could not save the location")
                })



    fun handleOnRemoveClick(locationWithName: LocationWithName) = locationRepository.deletePersistedLocation(locationWithName)
        .subscribe({
            Log.i(TAG, "Successfully removed a location")
        }, {
            Log.w(TAG, "Failed removing location : ${locationWithName.name}")
        })

}


