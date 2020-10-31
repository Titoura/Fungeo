package com.titou.database.dao

import androidx.room.*
import com.titou.database.models.City

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(vararg cities: City)

    @Update
    fun updateCities(vararg cities: City)

    @Delete
    fun deleteCities(vararg cities: City)

    @Query("SELECT * FROM cities")
    fun getAllCities(): Array<City>


}
