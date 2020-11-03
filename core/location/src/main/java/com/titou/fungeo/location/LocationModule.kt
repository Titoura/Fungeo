package com.titou.fungeo.location

import org.koin.dsl.module

object LocationModule {
    val module = module {
        single { LocationManager() }
    }
}
