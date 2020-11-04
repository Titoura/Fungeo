package com.titou.urgo.locations

import android.app.Activity
import com.titou.activity.MainActivity
import com.titou.fungeo.location.LocationManager
import com.titou.fungeo.weather.cqrs.api.WeatherApiService
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object LocationsModule {
    val module = module {
        scope<MainActivity> {

            fragment { LocationsFragment(getSource()) }

            scope<LocationsFragment> {
                scoped { LocationsPresenter(get()) }
                scoped { LocationsInteractor(get(), get(), get()) }
            }


        }
    }
}
