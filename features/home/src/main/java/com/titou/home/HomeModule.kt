package com.titou.home

import android.app.Activity
import com.titou.activity.MainActivity
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

object HomeModule {
    val module = module {
        scope<MainActivity> {
            fragment { HomeFragment() }

            scope<HomeFragment> {
                scoped { HomePresenter(get()) }
                scoped { HomeInteractor() }
            }


        }
    }
}
