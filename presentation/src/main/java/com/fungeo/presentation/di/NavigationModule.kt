package com.fungeo.presentation.di

import com.fungeo.presentation.activity.MainActivity
import com.fungeo.presentation.activity.ActivityRouter
import org.koin.dsl.binds
import org.koin.dsl.module


object NavigationModule {
    val module = module {

//        scope<MainActivity> {
//            scoped { ActivityRouter() } binds arrayOf(ActivityRouter::class)
//        }
    }
}

