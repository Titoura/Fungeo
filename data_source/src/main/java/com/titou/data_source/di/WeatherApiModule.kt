package com.titou.data_source.di

import com.titou.data_source.remote.weather_api.RetrofitBuilder
import com.titou.data_source.remote.weather_api.api.WeatherApiService
import com.titou.data_source.remote.weather_api.requests.RemoteWeatherDataSource
import org.koin.dsl.bind
import org.koin.dsl.module

object WeatherApiModule {
    val module = module {
        single { RetrofitBuilder() }
        single { RemoteWeatherDataSource(get()) } bind(com.titou.use_cases.RemoteWeatherDataSource::class)
        single {
            get<RetrofitBuilder>().retrofitInstance.create(
                WeatherApiService::class.java
            )
        }

    }
}
