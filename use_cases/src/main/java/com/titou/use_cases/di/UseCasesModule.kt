package com.titou.use_cases.di

import com.titou.use_cases.LocationRepository
import com.titou.use_cases.WeatherRepository
import org.koin.dsl.module

object UseCasesModule {
    val module = module {
        single { LocationRepository(get(), get()) }
        single { WeatherRepository(get()) }
    }
}
