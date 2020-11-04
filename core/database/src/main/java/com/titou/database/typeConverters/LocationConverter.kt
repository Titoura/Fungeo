package com.titou.database.typeConverters

import android.location.Location
import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.lang.Exception


class LocationConverter {
    @TypeConverter
    fun toLocation(json : String?): Location? {
        return try {
            val array = Gson().fromJson(json, Array<Double>::class.java)
            val location = Location("")
            array?.get(0)?.let{
                location.latitude = it
            }
            array?.get(1)?.let{
                location.longitude = it
            }
            location
        } catch (e : Exception) {
            null
        }
    }

    @TypeConverter
    fun toLocationString(location: Location?): String? {
        return Gson().toJson(arrayOf(location?.latitude, location?.longitude))
    }

}