package com.titou.fungeo.weather.repository

import com.titou.database.dao.LocationWithNAmeDao
import com.titou.database.models.LocationWithName
import com.titou.database.models.Weather
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class LocationWithNameRepository :KoinComponent{

    val dao: LocationWithNAmeDao by inject()

    fun insert(values: List<LocationWithName>): Completable {
        return dao.insert(*values.toTypedArray())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllLocations(): Observable<List<LocationWithName>> {
        return dao
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}