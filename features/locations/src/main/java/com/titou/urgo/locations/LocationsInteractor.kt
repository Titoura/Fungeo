package com.titou.urgo.locations

import com.titou.database.models.LocationWithName
import com.titou.fungeo.location.LocationManager
import com.titou.fungeo.weather.cqrs.GetWeatherQuery
import com.titou.fungeo.weather.repository.LocationWithNameRepository
import org.koin.core.KoinComponent

internal class LocationsInteractor(
    private val getWeatherQuery: GetWeatherQuery,
    private val locationManager: LocationManager,
    private val locationWithNameRepository: LocationWithNameRepository

) : KoinComponent {


    fun fetchWeatherFromServer(location: android.location.Location) = getWeatherQuery.fetchWeatherFromServerAndSave(location)
    fun getLocations() = locationWithNameRepository.getAllLocations()
    fun insertLocations(vararg locationWithName: LocationWithName) = locationWithNameRepository.insert(locationWithName.toList())

}
