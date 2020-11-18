package com.fungeo.presentation.di

import com.fungeo.presentation.activity.ActivityPresenter
import com.fungeo.presentation.activity.ActivityRouter
import org.koin.dsl.module

object ActivityModule {
    val module = module {
        single { ActivityPresenter(get(), get()) }
        single { ActivityRouter() }

    }
}
