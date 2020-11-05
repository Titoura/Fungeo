package com.titou.fungeo.settings

import com.titou.database.models.LocationWithName
import com.titou.fungeo.location.LocationManager
import com.titou.fungeo.weather.repository.LocationWithNameRepository
import org.koin.core.KoinComponent

internal class SettingsInteractor(
    private val locationWithNameRepository: LocationWithNameRepository,
    private val locationManager: LocationManager

) : KoinComponent {


    fun getDefaultLocation() = locationWithNameRepository.getDefaultLocation()
    fun getLocationPosition(locationName : String) = locationManager.searchLocationPosition(locationName, true)
    fun setDefaultLocation(locationWithName: LocationWithName) = locationWithNameRepository.insertDefaultLocation(locationWithName)
    fun clearDefaultLocation() = locationWithNameRepository.deleteDefaultLocation()
}
