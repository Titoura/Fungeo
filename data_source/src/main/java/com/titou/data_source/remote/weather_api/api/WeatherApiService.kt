package com.titou.data_source.remote.weather_api.api

import com.titou.data_source.remote.weather_api.dtos.WeatherResponseDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
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
