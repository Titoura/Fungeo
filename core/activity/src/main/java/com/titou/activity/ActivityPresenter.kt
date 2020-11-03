package com.titou.activity

import android.util.Log
import org.koin.core.KoinComponent
import org.koin.core.inject

class ActivityPresenter(
    private val router: ActivityRouter
) : KoinComponent {

    fun handleOnBottomMenuItemSelected(menu_item: Int, activity : MainActivity) =
        router.updateDisplayedFragment(menu_item, activity)
            .doOnError {
            Log.e("ActivityPresenter", it.localizedMessage?:"Error while displaying new fragment")
        }
}