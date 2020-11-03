package com.titou.fungeo.weather.cqrs.api

import com.titou.requestscontroller.RetrofitBuilder
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
