package com.titou.data_source.local.database.dao

import androidx.room.*
import com.titou.data_source.local.database.dtos.LocationWithNameRoomModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.koin.core.KoinComponent

@Dao
interface LocationWithNameDao : KoinComponent {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg locationWithName: LocationWithNameRoomModel) : Completable

    @Update
    fun update(vararg locationWithName: LocationWithNameRoomModel) : Completable

    @Delete
    fun delete(vararg locationWithName: LocationWithNameRoomModel) : Completable

    @Query("DELETE FROM location_with_name WHERE main")
    fun deleteDefaultLocation() : Completable

    @Query("SELECT * FROM location_with_name WHERE NOT main")
    fun getAll(): Observable<List<LocationWithNameRoomModel>>

    @Query("SELECT * FROM location_with_name WHERE main LIMIT 1")
    fun getMainLocation(): Observable<List<LocationWithNameRoomModel>>

}
