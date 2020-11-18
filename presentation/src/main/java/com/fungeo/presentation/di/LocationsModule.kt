package com.fungeo.presentation.di

import com.fungeo.presentation.activity.MainActivity
import com.fungeo.presentation.locations.LocationsFragment
import com.fungeo.presentation.locations.LocationsPresenter
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object LocationsModule {
    val module = module {
        scope<MainActivity> {

            fragment { LocationsFragment(getSource()) }

            scope<LocationsFragment> {
                scoped { LocationsPresenter(get(), get()) }
            }


        }
    }
}
