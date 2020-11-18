package com.titou.use_cases

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import com.fungeo.data.entity.LocationWithName
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import org.koin.core.KoinComponent


class LocationRepository(
    private val databaseLocationSource: DatabaseLocationSource,
    private val deviceLocationSource: DeviceLocationSource
) : KoinComponent {
    fun getAllPersistedLocations() = databaseLocationSource.getAllLocations()
    fun saveNewLocation(locationName: String) = getLocationForName(locationName)?.let { location ->  databaseLocationSource.insert(location)}
    fun deletePersistedLocation(location: LocationWithName) =
        databaseLocationSource.delete(location)

    fun saveDefaultLocation(locationName: String) = getLocationForName(locationName)?.let{ locWithName ->
        databaseLocationSource.insert(locWithName.copy(main = true))
    }

    fun deleteDefaultLocation() = databaseLocationSource.deleteDefaultLocation()
    fun getDefaultLocation() = databaseLocationSource.getDefaultLocation()

    fun getMainLocation() = getDefaultLocation().flatMap { locationAsAList ->
        if (locationAsAList.isEmpty()) {
            getCurrentLocationObservable().map {
                LocationWithName(
                    name = getLocationNameForPosition(it) ?: "Your position",
                    location = it,
                    countryCode = "",
                    main = true
                )
            }
        } else {
            locationAsAList.toObservable()
        }
    }

    fun build(appCompatActivity: AppCompatActivity) = deviceLocationSource.build(appCompatActivity)

    fun getCurrentLocationObservable() = deviceLocationSource.getCurrentLocationObservable()

    fun getLocationNameForPosition(location: Location) =
        deviceLocationSource.getLocationNameForPosition(location)

    fun getLocationForName(locationName: String) =
        deviceLocationSource.getLocationForName(locationName)
}


interface DatabaseLocationSource {

    fun insert(locationWithName: LocationWithName): Completable
    fun deleteDefaultLocation(): Completable
    fun delete(locationWithName: LocationWithName): Completable
    fun getAllLocations(): Observable<List<LocationWithName>>
    fun getDefaultLocation(): Observable<List<LocationWithName>>

}

interface DeviceLocationSource {

    // init
    fun requestDeviceLocation(): Location?
    fun requestLocationPermission(appCompatActivity: AppCompatActivity)
    fun build(appCompatActivity: AppCompatActivity)

    // Get device position
    fun getCurrentLocationObservable(): Observable<Location>

    // Geocoding
    fun getLocationNameForPosition(location: Location): String?
    fun getLocationForName(locationName: String): LocationWithName?

}
