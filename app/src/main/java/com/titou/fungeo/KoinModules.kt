package com.titou.fungeo

import com.fungeo.presentation.di.*
import com.titou.data_source.di.DatabaseModule
import com.titou.data_source.di.DeviceLocationModule
import com.titou.data_source.di.WeatherApiModule
import com.titou.use_cases.di.UseCasesModule

object KoinModules {

    fun toList() = listOf(
        ActivityModule.module,
        DatabaseModule.module,
        DeviceLocationModule.module,
        HomeModule.module,
        LocationsModule.module,
        NavigationModule.module,
        SettingsModule.module,
        WeatherApiModule.module,
        UseCasesModule.module
    )
}
