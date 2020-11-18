package com.fungeo.presentation.settings

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.fungeo.data.entity.LocationWithName
import com.titou.use_cases.LocationRepository
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject


internal class SettingsPresenter(
    private val locationRepository: LocationRepository
) : KoinComponent {

    private val context : Context by inject()

    fun props(): Observable<List<LocationWithName>> = locationRepository.getDefaultLocation()

    fun setDefaultLocation(locationName: String) =
        locationRepository.saveDefaultLocation(locationName)?.subscribe({
            Toast.makeText(context, "Default location was saved successfully", Toast.LENGTH_SHORT)
                .show()
        }, {
            Log.e("SettingsPresenter", it.localizedMessage ?: "Unknown error")
            Toast.makeText(context, "Failed saving your default location", Toast.LENGTH_SHORT)
                .show()
        })


    fun handleOnRemoveClick() = locationRepository.deleteDefaultLocation().subscribe({
        Toast.makeText(context, "Default location was removed successfully", Toast.LENGTH_SHORT)
            .show()
    }, {
        Log.e("SettingsPresenter", it.localizedMessage ?: "Unknown error")
        Toast.makeText(context, "Failed removing your default location", Toast.LENGTH_SHORT).show()
    })
}


