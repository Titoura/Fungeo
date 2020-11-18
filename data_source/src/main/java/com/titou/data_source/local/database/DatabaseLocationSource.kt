package com.titou.data_source.local.database

import android.location.Location
import com.fungeo.data.entity.LocationWithName
import com.titou.data_source.local.database.dao.LocationWithNameDao
import com.titou.data_source.local.database.dtos.LocationWithNameRoomModel
import com.titou.use_cases.DatabaseLocationSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent

class DatabaseLocationSource(
    private val locationWithNameDao: LocationWithNameDao
) : KoinComponent, DatabaseLocationSource {

    override fun insert(locationWithName: LocationWithName): Completable = locationWithNameDao.insert(
        LocationWithNameRoomModel(locationWithName)
    )

    override fun deleteDefaultLocation(): Completable = locationWithNameDao.deleteDefaultLocation()

    override fun delete(locationWithName: LocationWithName): Completable  = locationWithNameDao.delete(LocationWithNameRoomModel(locationWithName))

    override fun getAllLocations(): Observable<List<LocationWithName>>  = locationWithNameDao.getAll().map {locationList -> locationList.map{ it.toDomainLocationModel() }}

    override fun getDefaultLocation(): Observable<List<LocationWithName>> = locationWithNameDao.getMainLocation().map { locationList -> locationList.map{ it.toDomainLocationModel() }}

}