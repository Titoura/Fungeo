package com.titou.fungeo.navigation

import androidx.fragment.app.Fragment
import com.titou.activity.ActivityRouter
import com.titou.activity.R
import com.titou.home.HomeFragment
import io.reactivex.rxjava3.core.Single
import kotlin.system.exitProcess

// TODO : When app gets better, create a navigation module
class RootRouter : ActivityRouter{


    lateinit var currentFragment: Class<out Fragment>

    override fun updateDisplayedFragment(menu_item: Int): Single<Class<out Fragment>> =
        Single.just(
            when (menu_item) {
                R.id.navigation_home ->
                    HomeFragment::class.java
                R.id.navigation_locations ->
                    //FIXME : Display LocationsFragment
                    Fragment::class.java
                R.id.navigation_settings ->
                    //FIXME : Display SettingsFragment
                    HomeFragment::class.java
                else -> throw IllegalStateException("Error! This menu index should not be accessible. menu_item: $menu_item")

            }
        )
            .map {
                it.also { currentFragment = it }
            }


    override fun handleOnBackPressed() {
        if (currentFragment == HomeFragment::class.java) {
            //TODO: check behaviour and performances
            exitProcess(-1)
        } else {
            updateDisplayedFragment(R.id.navigation_home)
        }

    }
}