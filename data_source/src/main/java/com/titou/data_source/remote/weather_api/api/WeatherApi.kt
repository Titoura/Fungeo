package com.titou.data_source.remote.weather_api.api

import com.titou.data_source.remote.weather_api.RetrofitBuilder
import org.koin.core.KoinComponent
import org.koin.core.inject

object WeatherApi : KoinComponent {
    private val retrofit: RetrofitBuilder by inject()
    val retrofitService: WeatherApiService by lazy {
        retrofit.retrofitInstance.create(
            WeatherApiService::class.java
        )
    }
}
