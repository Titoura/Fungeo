package com.titou.fungeo

import com.titou.activity.ActivityModule
import com.titou.database.DatabaseModule
import com.titou.fungeo.location.LocationModule
import com.titou.fungeo.navigation.NavigationModule
import com.titou.home.HomeModule
import com.titou.requestscontroller.RetrofitModule
import com.titou.fungeo.weather.WeatherModule
import com.titou.fungeo.settings.SettingsModule
import com.titou.urgo.locations.LocationsModule

object KoinModules {

    fun toList() = listOf(
        ActivityModule.module,
        HomeModule.module,
        NavigationModule.module,
        RetrofitModule.module,
        WeatherModule.module,
        LocationModule.module,
        DatabaseModule.module,
        SettingsModule.module,
        LocationsModule.module

    )
}
