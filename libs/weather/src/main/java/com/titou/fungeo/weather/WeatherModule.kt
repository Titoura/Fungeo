package com.titou.fungeo.weather

import com.titou.activity.MainActivity
import com.titou.requestscontroller.RetrofitBuilder
import com.titou.fungeo.weather.cqrs.GetWeatherQuery
import com.titou.fungeo.weather.cqrs.api.WeatherApiService
import com.titou.fungeo.weather.repository.LocationWithNameRepository
import org.koin.dsl.module

object WeatherModule {

    val module = module {

        single { LocationWithNameRepository() }

        scope<MainActivity> {
            factory { GetWeatherQuery( get<RetrofitBuilder>().retrofitInstance.create(WeatherApiService::class.java)) }
        }
    }
}
