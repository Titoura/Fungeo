package com.titou.fungeo

import android.app.Application
import com.facebook.soloader.SoLoader
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)

        val koinApplication = startKoin {
            fragmentFactory()
            androidContext(this@Application)
            modules(KoinModules.toList())
        }

    }
}
