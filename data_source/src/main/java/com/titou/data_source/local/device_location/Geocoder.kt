package com.titou.data_source.local.device_location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.IOException

class Geocoder : KoinComponent{

    private val context : Context by inject()

    fun searchLocationNameForPosition(location: Location): String? {

        var addressList: List<Address> = emptyList()


        val geoCoder = Geocoder(context)
        try {
            addressList = geoCoder.getFromLocation(location.latitude, location.longitude, 1)

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return addressList.firstOrNull()?.locality
    }



    fun getAddressByName(locationName: String): Address? {

        var addressList: List<Address> = emptyList()

        try {
            addressList = Geocoder(context).getFromLocationName(locationName, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return addressList.firstOrNull()
    }

}