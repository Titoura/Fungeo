package com.titou.fungeo

import com.titou.activity.ActivityModule
import com.titou.fungeo.location.LocationModule
import com.titou.fungeo.navigation.NavigationModule
import com.titou.home.HomeModule
import com.titou.requestscontroller.RetrofitModule

object KoinModules {

    fun toList() = listOf(
        ActivityModule.module,
        HomeModule.module,
        NavigationModule.module,
        RetrofitModule.module,
        LocationModule.module

    )
}
