package com.titou.fungeo.weather.repository

import androidx.room.rxjava3.EmptyResultSetException
import com.titou.database.dao.LocationWithNameDao
import com.titou.database.models.LocationWithName
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*


class LocationWithNameRepository : KoinComponent {

    val dao: LocationWithNameDao by inject()

    fun insert(value: LocationWithName): Completable {
        return dao.insert(value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }

    fun insertDefaultLocation(value: LocationWithName): Completable {
        value.main = true
        return dao.deleteDefaultLocation().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).andThen(
                dao.insert(value)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            )

    }

    fun deleteDefaultLocation(): Completable {
        return dao.deleteDefaultLocation().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    fun delete(value: LocationWithName): Completable {
        return dao.delete(value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllLocations(): Observable<List<LocationWithName>> {
        return dao
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getDefaultLocation(): Observable<List<LocationWithName>> {
        return dao
            .getMainLocation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            }
    }
data class Optional<T>(val value: T?)

fun <T> T?.carry() = Optional(this)
