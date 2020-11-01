package com.titou.activity

import org.koin.dsl.module

object ActivityModule {
    val module = module {
        scope<MainActivity> {
            scoped { ActivityPresenter(get()) }
            scoped { ActivityInteractor() }

        }
    }
}
