package com.fungeo.presentation.activity

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.titou.use_cases.LocationRepository
import org.koin.core.KoinComponent

class ActivityPresenter(
    private val router: ActivityRouter,
    private val locationRepository: LocationRepository
) : KoinComponent {

    fun handleOnBottomMenuItemSelected(menu_item: Int, activity : MainActivity) =
        router.updateDisplayedFragment(menu_item, activity)
            .doOnError {
            Log.e("ActivityPresenter", it.localizedMessage?:"Error while displaying new fragment")
        }

    fun buildDeviceLocationManager(appCompatActivity : AppCompatActivity) = locationRepository.build(appCompatActivity)
    fun getCurrentLocationObservable() = locationRepository.getCurrentLocationObservable()
}