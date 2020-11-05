package com.titou.fungeo.settings

import android.R
import android.R.layout
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.titou.database.models.LocationWithName
import com.titou.fungeo.weather.repository.Optional
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.koin.core.KoinComponent


internal class SettingsPresenter(
    private val interactor: SettingsInteractor
) : KoinComponent {


    fun props(): Observable<List<LocationWithName>> = interactor.getDefaultLocation()

    fun setDefaultLocation(locationName: String, context: Context) = interactor.setDefaultLocation(
            interactor.getLocationPosition(
                locationName
        )
    ).subscribe({
        Toast.makeText(context, "Default location was saved successfully", Toast.LENGTH_SHORT).show()
    }, {
        Log.e("SettingsPresenter", it.localizedMessage)
         Toast.makeText(context, "Failed saving your default location", Toast.LENGTH_SHORT).show()
    })

    fun handleOnRemoveClick(context: Context) = interactor.clearDefaultLocation().subscribe({
        Toast.makeText(context, "Default location was removed successfully", Toast.LENGTH_SHORT).show()
    }, {
        Log.e("SettingsPresenter", it.localizedMessage)
         Toast.makeText(context, "Failed removing your default location", Toast.LENGTH_SHORT).show()
    })
}


