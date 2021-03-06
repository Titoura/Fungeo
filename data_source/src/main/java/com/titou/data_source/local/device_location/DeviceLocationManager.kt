package com.titou.data_source.local.device_location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.fungeo.data.entity.LocationWithName
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.titou.data_source.R
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import org.koin.core.KoinComponent
import java.io.IOException


class DeviceLocationManager() : KoinComponent {

    lateinit var fusedLocationClient: FusedLocationProviderClient


    lateinit var context: Context

    fun requestLocationPermission(appCompatActivity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            appCompatActivity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            appCompatActivity.resources.getInteger(R.integer.location_permission_request_code)
        )
    }


    fun build(appCompatActivity: AppCompatActivity) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(appCompatActivity)
        if (appCompatActivity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission(appCompatActivity)
        }
        requestLocation()

        context = appCompatActivity

    }

    // TODO: Make a stronger request to location so that he location is fetched even f not accessed by another app
    @SuppressLint("MissingPermission")
    fun getCurrentLocationObservable(): Observable<Location> {
        return Observable.create { emitter ->

            val completeListener = getOnCompleteListener(emitter)
            try {
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    if (!emitter.isDisposed && it != null) emitter.onNext(it)
                }

                fusedLocationClient.lastLocation.addOnCompleteListener(completeListener)

            } catch (e: Exception) {
                emitter.tryOnError(e)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun requestLocation(): Location? {
        return try {
            fusedLocationClient.lastLocation.result
        } catch (e: java.lang.Exception) {
            null
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


}

data class Optional<T>(val value: T?)

fun <T> T?.carry() = Optional(this)



