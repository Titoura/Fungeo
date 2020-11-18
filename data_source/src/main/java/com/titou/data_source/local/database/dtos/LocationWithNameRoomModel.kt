package com.titou.data_source.local.database.dtos

import android.location.Location
import androidx.room.*
import com.fungeo.data.entity.LocationWithName

@Entity(tableName = "location_with_name")
data class LocationWithNameRoomModel(
    @PrimaryKey
    val name : String,
    val location: Location,
    val countryCode: String,
    var main: Boolean
)
{
    constructor(locationWithName: LocationWithName) : this(locationWithName.name, locationWithName.location, locationWithName.countryCode, locationWithName.main)

    fun toDomainLocationModel() = LocationWithName(name, location, countryCode, main)
}

