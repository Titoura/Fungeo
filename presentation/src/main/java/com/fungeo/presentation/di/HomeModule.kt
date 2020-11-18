package com.fungeo.presentation.di

import com.fungeo.presentation.activity.MainActivity
import com.fungeo.presentation.home.HomeFragment
import com.fungeo.presentation.home.HomePresenter
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object HomeModule {
    val module = module {
        scope<MainActivity> {
            fragment { HomeFragment(getSource()) }
            scope<HomeFragment> {  }
        }

        single { HomePresenter(get(), get()) }

    }
}
