package com.titou.home

import android.util.Log
import com.titou.database.models.City
import com.titou.database.models.CurrentWeather
import com.titou.database.models.DatedWeatherForecast
import com.titou.database.models.WeatherWithCity
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent
import java.time.LocalDate

internal class HomePresenter(
    private val interactor: HomeInteractor
) : KoinComponent {

    private val TAG = "HomePresenter"

    fun props(): Observable<Props> = Observable.just(mockProps())
        .map {
            Props(it)
        }


    // FIXME: remove temporary mock, used for dev only
    // TODO: reuse in unit tests
    fun mockProps(): WeatherWithCity {
        val city = City("France", "Paris")
        val currentWeather = CurrentWeather("1969-09-14T10:15:30Z", "Sunny", 30, 15)
        val weatherForecast = listOf(DatedWeatherForecast(LocalDate.now(), 10, 20, 15))

        return WeatherWithCity(
            city = city,
            currentWeather = currentWeather,
            weatherForecast = weatherForecast
        )
    }
}

