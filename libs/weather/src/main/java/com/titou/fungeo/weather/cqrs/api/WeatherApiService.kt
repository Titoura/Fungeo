package com.titou.fungeo.weather.cqrs.api

import com.titou.fungeo.weather.cqrs.dtos.WeatherResponseDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {

    @GET("onecall")
    fun getWeatherForLocation(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("exclude") exclusions: String,
        @Query("appid") api_key: String
    ):
            Observable<Response<WeatherResponseDto>>
}
