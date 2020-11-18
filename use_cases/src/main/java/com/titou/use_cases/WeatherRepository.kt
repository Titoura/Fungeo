package com.titou.use_cases

import android.location.Location
import com.fungeo.data.entity.LocationWithName
import com.fungeo.data.entity.Weather
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent


class WeatherRepository (
    private val weatherSource: RemoteWeatherDataSource
): KoinComponent
{
    fun getWeatherForLocation(locationWithName: LocationWithName) = weatherSource.getWeatherForLocation(locationWithName.location)
}


interface RemoteWeatherDataSource {
    fun getWeatherForLocation(location : Location): Observable<Weather>
}
