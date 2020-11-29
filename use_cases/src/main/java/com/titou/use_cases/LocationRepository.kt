package com.titou.use_cases

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import com.fungeo.data.entity.LocationWithName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.KoinComponent


class LocationRepository(
    private val databaseLocationSource: DatabaseLocationSource,
    private val deviceLocationSource: DeviceLocationSource
) : KoinComponent {
    fun getAllPersistedLocations() =
        databaseLocationSource.getAllLocations().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun saveNewLocation(locationName: String) =
        getLocationForName(locationName)?.let { location -> databaseLocationSource.insert(location) }
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())


    fun deletePersistedLocation(location: LocationWithName) =
        databaseLocationSource.delete(location).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


    fun saveDefaultLocation(locationName: String) =
        getLocationForName(locationName)?.let { locWithName ->
            databaseLocationSource.insert(locWithName.copy(main = true))
        }?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())


    fun deleteDefaultLocation() =
        databaseLocationSource.deleteDefaultLocation().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getDefaultLocation() =
        databaseLocationSource.getDefaultLocation().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


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
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    }

    fun build(appCompatActivity: AppCompatActivity) = deviceLocationSource.build(appCompatActivity)

    fun getCurrentLocationObservable() = deviceLocationSource.getCurrentLocationObservable()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())


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
