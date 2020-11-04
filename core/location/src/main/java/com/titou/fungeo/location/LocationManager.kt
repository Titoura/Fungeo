package com.titou.fungeo.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.functions.Cancellable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import org.koin.core.KoinComponent
import java.io.IOException


class LocationManager {

    lateinit var fusedLocationClient: FusedLocationProviderClient


    lateinit var context: Context

    fun requestLocationPermission(appCompatActivity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            appCompatActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            appCompatActivity.resources.getInteger(R.integer.location_permission_request_code)
        )
    }


    fun requestLocation(): Observable<Optional<Location>>? {
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return Observable.just(Optional(fusedLocationClient.lastLocation.result))
        }
        return null
    }

    fun build(appCompatActivity: AppCompatActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appCompatActivity)
        if (appCompatActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission(appCompatActivity)
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
    @SuppressLint("MissingPermission")
    fun getCurrentLocationObservable(): Observable<Location> {
        return Observable.create { emitter ->

            val completeListener = getOnCompleteListener(emitter)
            try {
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    if (!emitter.isDisposed && it != null) emitter.onNext(it)
                }
                val task = fusedLocationClient.lastLocation
                task.addOnCompleteListener(completeListener)
            } catch (e: Exception) {
                emitter.tryOnError(e)
            }
        }
    }


    private fun getOnCompleteListener(emitter: ObservableEmitter<Location>): OnCompleteListener<Location> {
        return OnCompleteListener { task ->
            if (!task.isSuccessful) {
                emitter.tryOnError(
                    task.exception
                        ?: IllegalStateException("Can't get location from FusedLocationProviderClient")
                )
            }
        }
    }

    fun searchLocationName(location: Location): String? {

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



