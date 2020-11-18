package com.titou.data_source.remote.weather_api.requests;

import android.location.Location
import com.fungeo.data.entity.Weather
import com.titou.data_source.remote.weather_api.api.WeatherApiService
import com.titou.data_source.remote.weather_api.mapper.toWeatherModel
import com.titou.use_cases.RemoteWeatherDataSource
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent


class RemoteWeatherDataSource(
    private val weatherApiService: WeatherApiService
) : KoinComponent, RemoteWeatherDataSource {

    //FIXME: extract api key
    private val API_KEY = "32349591b103bde8f9faa1809035c44e"

    override fun getWeatherForLocation(location: Location): Observable<Weather> {
        return weatherApiService.getWeatherForLocation(
            longitude = location.longitude.toFloat(),
            latitude = location.latitude.toFloat(),
            api_key = API_KEY,
            exclusions = ""
        )
            .map {
                it.body()
                    ?: throw IllegalStateException("Error parsing FetchWeather() JSON body")
            }
            .map {
                it.toWeatherModel()
            }
    }
}

