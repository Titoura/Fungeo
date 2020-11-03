package com.titou.home

import android.app.Activity
import com.titou.activity.MainActivity
import com.titou.fungeo.location.LocationManager
import com.titou.fungeo.weather.cqrs.api.WeatherApiService
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object HomeModule {
    val module = module {
        scope<MainActivity> {

            fragment { HomeFragment(getSource()) }

            scope<HomeFragment> {
                scoped { HomePresenter(get()) }
                scoped { HomeInteractor(get(), get()) }
            }


        }
    }
}
