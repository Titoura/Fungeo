package com.titou.urgo.locations

import com.titou.fungeo.location.LocationManager
import com.titou.fungeo.weather.cqrs.GetWeatherQuery
import org.koin.core.KoinComponent

internal class LocationsInteractor(
    private val getWeatherQuery: GetWeatherQuery,
    private val locationManager: LocationManager

) : KoinComponent {


    fun fetchWeatherFromServer(location: android.location.Location) = getWeatherQuery.fetchWeatherFromServerAndSave(location)
    fun getLastLocation() = locationManager.getCurrentLocationObservable()
    fun getCurrentLocationName(location : android.location.Location) = locationManager.searchLocationName(location)
   // fun getWeatherFromDatabase(location : Location) = getWeatherQuery.getWeatherFromDatabase(location)

}