package com.titou.database.dao

import androidx.room.*
import com.titou.database.models.City
import com.titou.database.models.CurrentWeather
import com.titou.database.models.DatedWeatherForecast

@Dao
interface DatedWeatherForecastDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatedWeatherForecasts(vararg datedWeatherForecasts: DatedWeatherForecast)

    @Update
    fun updateDatedWeatherForecasts(vararg datedWeatherForecasts: DatedWeatherForecast)

    @Delete
    fun deleteDatedWeatherForecasts(vararg datedWeatherForecasts: DatedWeatherForecast)

    @Query("SELECT * FROM dated_weather_forecast")
    fun getAllDatedWeatherForecasts(): Array<DatedWeatherForecast>

}
