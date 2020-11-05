package com.titou.home

import android.location.Location
import com.titou.database.models.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.koin.core.KoinComponent
import java.time.LocalDate
import java.time.LocalDateTime

internal class HomePresenter(
    private val interactor: HomeInteractor
) : KoinComponent {

    private val TAG = "HomePresenter"

    //TODO: Load default location
    fun props(): Observable<Props> {

        return interactor.getDefaultLocation()
            .flatMap {locationAsAList->

                if(locationAsAList.isEmpty()) {
                    interactor.getLastLocation().flatMap { currentLocation ->
                        currentLocation.let {
                            interactor.fetchWeatherFromServer(it)
                                .map {
                                    currentLocation?.let { location: Location ->
                                        Props(
                                            weather = it,
                                            location = location,
                                            locationName = interactor.getCurrentLocationName(
                                                location
                                            )
                                                ?: "Your weather"
                                        )
                                    }
                                }
                        }
                    }
                }
                else{
                    locationAsAList.first().location?.let {
                        interactor.fetchWeatherFromServer(it)
                            .map { weather ->
                                Props(
                                        weather = weather,
                                        location = locationAsAList.first().location,
                                        locationName = locationAsAList.first().name
                                )
                            }
                    }

                }
            }
    }

}




