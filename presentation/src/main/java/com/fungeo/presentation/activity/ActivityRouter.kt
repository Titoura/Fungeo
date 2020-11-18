package com.fungeo.presentation.activity

import androidx.fragment.app.Fragment
import com.fungeo.presentation.R
import com.fungeo.presentation.activity.MainActivity
import com.fungeo.presentation.home.HomeFragment
import io.reactivex.rxjava3.core.Single
import com.fungeo.presentation.locations.LocationsFragment

// TODO : When app gets bigger, create a com.fungeo.presentation.navigation module
class ActivityRouter {

    fun updateDisplayedFragment(menu_item: Int, activity : MainActivity): Single<Fragment> = Single.just(
        when (menu_item) {
            R.id.navigation_home ->
                HomeFragment(activity)
            R.id.navigation_locations ->
                LocationsFragment(activity)
            R.id.navigation_settings ->
                com.fungeo.presentation.settings.SettingsFragment(activity)
            else -> throw IllegalStateException("Error! This menu index should not be accessible. menu_item: $menu_item")
        }
    ).map {
        it
    }

    fun handleOnBackPressed() {}
}