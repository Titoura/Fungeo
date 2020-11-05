package com.titou.fungeo.settings

import com.titou.activity.MainActivity
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object SettingsModule {
    val module = module {
        scope<MainActivity> {

            fragment { SettingsFragment(getSource()) }

            scope<SettingsFragment> {
                scoped { SettingsPresenter(get()) }
                scoped { SettingsInteractor(get(), get()) }
            }


        }
    }
}
