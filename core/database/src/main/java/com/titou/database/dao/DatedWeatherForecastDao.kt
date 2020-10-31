package com.titou.database.dao

import androidx.room.*
import com.titou.database.models.City
import com.titou.database.models.CurrentWeather

@Dao
interface CurrentWeatherDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeathers(vararg currentWeathers: CurrentWeather)

    @Update
    fun updateCurrentWeathers(vararg currentWeathers: CurrentWeather)

    @Delete
    fun deleteCurrentWeathers(vararg currentWeathers: CurrentWeather)

    @Query("SELECT * FROM current_weather")
    fun getAllCurrentWeathers(): Array<CurrentWeather>

}
