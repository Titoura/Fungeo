package com.titou.database.dao

import androidx.room.*
import com.titou.database.models.CurrentWeather
import com.titou.database.models.WeatherWithCity

@Dao
interface WeatherWithCityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherWithCities(vararg weatherWithCities: WeatherWithCity)

    @Update
    fun updateWeatherWithCities(vararg weatherWithCities: WeatherWithCity)

    @Delete
    fun deleteWeatherWithCities(vararg weatherWithCities: WeatherWithCity)

    @Query("SELECT * FROM current_weather")
    fun getAllWeatherWithCities(): Array<CurrentWeather>

}
