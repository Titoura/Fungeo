package com.fungeo.presentation.home

import com.titou.use_cases.LocationRepository
import com.titou.use_cases.WeatherRepository
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent

internal class HomePresenter(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository
) : KoinComponent {

    private val TAG = "HomePresenter"

    fun props(): Observable<Props> = locationRepository.getMainLocation().flatMap { location ->
        weatherRepository.getWeatherForLocation(location)
            .map {
                Props(
                    weather = it,
                    location = location.location,
                    locationName = location.name
                )
            }
    }

}




