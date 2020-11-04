package com.titou.urgo.locations

import com.facebook.litho.annotations.Prop
import com.titou.database.models.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.merge
import io.reactivex.rxjava3.kotlin.toObservable
import org.koin.core.KoinComponent
import java.time.LocalDate
import java.time.LocalDateTime

internal class LocationsPresenter(
    private val interactor: LocationsInteractor
) : KoinComponent {

    private val TAG = "HomePresenter"

    fun props(): Observable<List<LocationWithNameAndWeather>> {

        //TODO: CREATE ABILITY TO CHOOSE CITY
        val locations = listOf(
            LocationWithName("Paris", 48.8534f, 2.3488f),
            LocationWithName("New-York", 40.730610f, -73.935242f),
            LocationWithName("Berlin", 52.520008f, 13.404954f),
            LocationWithName("Marseille", 43.29695f, 5.38107f)
        )

        interactor.insertLocations(*locations.toTypedArray()).subscribe({},{})





        return interactor.getLocations().flatMap {
            it.map { locationWithName ->
                interactor.fetchWeatherFromServer(
                    locationWithName.location
                        ?: throw IllegalStateException("Location should not be null")
                )
                    .map {weather ->
                        LocationWithNameAndWeather(
                            weather,
                            locationName = locationWithName.name
                                ?: throw IllegalStateException("Location name should not be null"),
                            location = locationWithName.location
                                ?: throw IllegalStateException("Location coordinates should not be null")
                        )
                    }

            }.merge().toList().toObservable()
        }


//            .let{
//            it.map {  }
//            Props(
//                weatherList = weatherList,
//                location = locationWithCity.location
//                    ?: throw IllegalStateException("City location should not be null"),
//                locationName = locationWithCity.name
//                    ?: "Paris"
//            )
//            map { it }}
    }

//            .map { it }
//            .flatMapIterable {
//            )
    // Observable<Transmission>
//            .toList() // Single<List<Transmission>>
//            .toObservable() // Observable<List<Transmission>>


//        return Observable.just(Props(mockProps()))
//        return locations.map { locationWithCity ->
//            interactor.fetchWeatherFromServer(
//                locationWithCity.location
//                    ?: throw IllegalStateException("City location should not be null")
//            )
//        }.apply {
//
//        }
//
//
//
//            .toList().map { weatherList ->
//            Props(
//                weatherList = weatherList,
//                location = locationWithCity.location
//                    ?: throw IllegalStateException("City location should not be null"),
//                locationName = locationWithCity.name
//                    ?: "Paris"
//            )
//        }.toObservable()

}

//}


//    //TODO: make this a completable also to display toast error message from the fragment
//    fun fetchWeatherForLocation(location: Location){
//        val paris = Location(2.3488f, 48.8534f)
//
//        interactor.fetchWeatherFromServer(paris).subscribe({
//            Log.i(TAG, "Successfully fetched weather from the server for location : $location")
//        },{
//            Log.e(TAG, "Failed fetching weather for location : $location. \n" +
//                    "Error : ${it.localizedMessage}")
//        })
//    }

// FIXME: remove temporary mock, used for dev only
// TODO: reuse in unit tests
fun mockProps(): Weather {
    val longitude = 2.3488f
    val latitude = 48.8534f
    val currentWeather = CurrentWeather(
        LocalDateTime.now(),
        listOf(WeatherDescription("Sunny", "Sunny")),
        30f,
        15f
    )
    val weatherForecast = listOf(
        DatedWeatherForecast(
            LocalDate.now(),
            TemperatureForecast(15f, 15f, 15f, 15f),
            PerceivedTemperatureForecast(15f, 15f),
            listOf(WeatherDescription("Sunny", "Sunny"))
        )
    )

    return Weather(
        city = "Paris",
        latitude = latitude,
        longitude = longitude,
        currentWeather = currentWeather,
        weatherForecast = weatherForecast
    )
}


