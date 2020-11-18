package com.titou.data_source.di

import android.location.LocationManager
import com.titou.data_source.local.device_location.DeviceLocationManager
import com.titou.data_source.local.device_location.DeviceLocationSource
import com.titou.data_source.local.device_location.Geocoder
import org.koin.dsl.bind
import org.koin.dsl.module

object DeviceLocationModule {
    val module = module {
        single { DeviceLocationManager() }
        single { DeviceLocationSource(get(), get()) } bind(com.titou.use_cases.DeviceLocationSource::class)
        single { Geocoder() }
    }
}
