package com.fungeo.presentation.di

import com.fungeo.presentation.activity.MainActivity
import com.fungeo.presentation.settings.SettingsFragment
import com.fungeo.presentation.settings.SettingsPresenter
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object SettingsModule {
    val module = module {
        scope<MainActivity> {

            fragment { SettingsFragment(getSource()) }

            scope<SettingsFragment> {
                scoped { SettingsPresenter(get()) }
            }


        }
    }
}
