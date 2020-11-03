package com.titou.fungeo.weather.repository

import com.titou.database.dao.WeatherDao
import com.titou.database.models.Location
import com.titou.database.models.Weather
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherRepository(val dao: WeatherDao) {

    fun insert(values: List<Weather>): Completable? {
        return dao.insert(*values.toTypedArray())
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllWeathers(): Observable<List<Weather>>? {
        return dao
            .getAll()
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getWeatherForLocation(
        location: Location
    ): Observable<Weather>? {
        return dao
            .getWeatherForLocation(location.longitude, location.latitude)
            .subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())

    }
}