package com.titou.database.models

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class LocationWithName(
    val name : String? = null,
    val location: Location? = null
)
{
    constructor(name:String, longitude: Float, latitude : Float) : this(name, Location("").apply { this.latitude = latitude.toDouble() }.apply { this.longitude = longitude.toDouble() })
}
