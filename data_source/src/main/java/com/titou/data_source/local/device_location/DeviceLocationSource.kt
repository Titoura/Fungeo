package com.titou.data_source.local.device_location

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import com.fungeo.data.entity.LocationWithName
import com.titou.use_cases.DeviceLocationSource
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent


class DeviceLocationSource(
    private val deviceLocationManager: DeviceLocationManager,
    private val geoCoder: Geocoder
) : KoinComponent, DeviceLocationSource {

    override fun requestLocationPermission(appCompatActivity: AppCompatActivity) =
        deviceLocationManager.requestLocationPermission(appCompatActivity)

    override fun build(appCompatActivity: AppCompatActivity) =
        deviceLocationManager.build(appCompatActivity)

    override fun getLocationForName(locationName: String): LocationWithName? {
        val address = geoCoder.getAddressByName(locationName)
        val location = Location("")
        location.latitude = address?.latitude
            ?: throw java.lang.Exception("We could not find a location matching the name you entered. Please try a less specific position.")
        location.longitude = address.longitude
        val newName = address.locality ?: locationName.toLowerCase().capitalize()
        val countryCode = address.countryCode ?: ""

        // TODO: Use Dto
        return LocationWithName(newName, location, countryCode, false)
    }

    override fun getCurrentLocationObservable() : Observable<Location> =
        deviceLocationManager.getCurrentLocationObservable()

    override fun requestDeviceLocation(): Location? = deviceLocationManager.requestLocation()

    override fun getLocationNameForPosition(location: Location): String? =
        geoCoder.searchLocationNameForPosition(location)


}

