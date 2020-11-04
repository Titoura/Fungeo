package com.titou.fungeo.navigation

import androidx.fragment.app.Fragment
import com.titou.activity.ActivityRouter
import com.titou.activity.MainActivity
import com.titou.activity.R
import com.titou.home.HomeFragment
import com.titou.urgo.locations.LocationsFragment
import io.reactivex.rxjava3.core.Single
import kotlin.system.exitProcess

// TODO : When app gets bigger, create a navigation module
class RootRouter : ActivityRouter {


    lateinit var currentFragment: Fragment

    override fun updateDisplayedFragment(menu_item: Int, activity : MainActivity): Single<Fragment> = Single.just(
        when (menu_item) {
            R.id.navigation_home ->
                HomeFragment(activity)
            R.id.navigation_locations ->
                //FIXME : Display LocationsFragment
                LocationsFragment(activity)
            R.id.navigation_settings ->
                //FIXME : Display SettingsFragment
                HomeFragment(activity)
            else -> throw IllegalStateException("Error! This menu index should not be accessible. menu_item: $menu_item")
        }
    ).map {
        it
    }


    override fun handleOnBackPressed() {}
}