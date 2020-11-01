package com.titou.fungeo.navigation

import com.titou.activity.ActivityRouter
import com.titou.activity.MainActivity
import org.koin.dsl.binds
import org.koin.dsl.module


object NavigationModule {
    val module = module {

        scope<MainActivity> {
            scoped { RootRouter() } binds  arrayOf(
                RootRouter::class,
                ActivityRouter::class
            )
        }
    }
}

