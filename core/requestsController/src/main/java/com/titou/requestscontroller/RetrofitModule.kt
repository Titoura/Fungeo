package com.titou.requestscontroller

import org.koin.dsl.module

object RetrofitModule {
    val module = module {
        single { RetrofitBuilder() }
    }
}
