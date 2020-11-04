package com.titou.database.dao

import androidx.room.*
import com.titou.database.models.CurrentWeather
import com.titou.database.models.LocationWithName
import com.titou.database.models.Weather
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent

@Dao
interface LocationWithNAmeDao : KoinComponent {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg locationWithName: LocationWithName) : Completable

    @Update
    fun updateWeather(vararg locationWithName: LocationWithName) : Completable

    @Delete
    fun deleteWeather(vararg locationWithName: LocationWithName) : Completable

    @Query("SELECT * FROM location_with_name")
    fun getAll(): Observable<List<LocationWithName>>

}
