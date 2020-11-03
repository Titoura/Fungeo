package com.titou.fungeo.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.core.KoinComponent
import java.io.IOException


class LocationManager {

    lateinit var fusedLocationClient: FusedLocationProviderClient

    var currentLocation: Optional<Location> = Optional(null)
        set(value) {
            currentLocationSubject.onNext(value)
            field = value
        }

    fun getCurrentLocationObservable(): Observable<Optional<Location>> = currentLocationSubject
    private val currentLocationSubject =
        BehaviorSubject.createDefault<Optional<Location>>(currentLocation)


    lateinit var context: Context
    fun requestLocationPermission(appCompatActivity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            appCompatActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            appCompatActivity.resources.getInteger(R.integer.location_permission_request_code)
        )
    }

    fun requestLocation(appCompatActivity: AppCompatActivity): Observable<Optional<Location>>? {
        if (appCompatActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            currentLocation =  Optional(fusedLocationClient.lastLocation.result)
            return Observable.just(currentLocation)
        }
        return null
    }

    fun build(appCompatActivity: AppCompatActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appCompatActivity)
        if (appCompatActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission(appCompatActivity)
        } else {
            fusedLocationClient.lastLocation.addOnCompleteListener {
                currentLocation = Optional(it.result)
            }
        }

        context = appCompatActivity

    }

    fun searchLocationPosition(locationName: String): Location {

        var addressList: List<Address> = emptyList()


        val geoCoder = Geocoder(context)
        try {
            addressList = geoCoder.getFromLocationName(locationName, 1)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        val location = Location("")
        location.latitude = addressList.firstOrNull()?.latitude ?: 0.0
        location.longitude = addressList.firstOrNull()?.longitude ?: 0.0

        return location
    }

//    fun searchCurrentLocation(): Observable<String> {
//
//        var addressList: List<Address> = emptyList()
//
//
//        val geoCoder = Geocoder(context)
//        try {
//            addressList =
//                geoCoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)
//
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//
//        return addressList.firstOrNull()?.locality
//    }

    fun searchLocationName(location : Location): String? {

        var addressList: List<Address> = emptyList()


        val geoCoder = Geocoder(context)
        try {
            addressList = geoCoder.getFromLocation(location.latitude, location.longitude, 1)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        val loc = searchLocationPosition("Paris")
        return addressList.firstOrNull()?.locality
    }


}

data class Optional<T>(val value: T?)
fun <T> T?.carry() = Optional(this)



