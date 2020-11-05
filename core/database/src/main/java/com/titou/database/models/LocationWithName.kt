package com.titou.database.models

import android.location.Location
import androidx.room.*
import com.google.gson.Gson
import java.util.*

@Entity(tableName = "location_with_name")
data class LocationWithName(
    @PrimaryKey
    val name : String,
    val location: Location?,
    val countryCode: String,
    var main: Boolean
)
{
    constructor(name:String, latitude : Float, longitude: Float, countryCode:String,main: Boolean) : this(name, Location("").apply { this.latitude = latitude.toDouble() }.apply { this.longitude = longitude.toDouble() }, countryCode, main)
}

