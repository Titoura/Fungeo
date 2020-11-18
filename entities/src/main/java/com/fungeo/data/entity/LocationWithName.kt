package com.fungeo.data.entity

import android.location.Location

data class LocationWithName(
    val name : String,
    val location: Location,
    val countryCode: String,
    var main: Boolean
)
{
    constructor(name:String, latitude : Float, longitude: Float, countryCode:String,main: Boolean) : this(name, Location("").apply { this.latitude = latitude.toDouble() }.apply { this.longitude = longitude.toDouble() }, countryCode, main)
}

