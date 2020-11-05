package com.titou.database.dao

import androidx.room.*
import com.titou.database.models.CurrentWeather
import com.titou.database.models.LocationWithName
import com.titou.database.models.Weather
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.koin.core.KoinComponent

@Dao
interface LocationWithNameDao : KoinComponent {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg locationWithName: LocationWithName) : Completable

    @Update
    fun update(vararg locationWithName: LocationWithName) : Completable

    @Delete
    fun delete(vararg locationWithName: LocationWithName) : Completable

    @Query("DELETE FROM location_with_name WHERE main")
    fun deleteDefaultLocation() : Completable

    @Query("SELECT * FROM location_with_name WHERE NOT main")
    fun getAll(): Observable<List<LocationWithName>>

    @Query("SELECT * FROM location_with_name WHERE main LIMIT 1")
    fun getMainLocation(): Observable<List<LocationWithName>>

}
