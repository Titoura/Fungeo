package com.titou.activity

import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.core.Single

interface ActivityRouter {

    fun updateDisplayedFragment(menu_item: Int, activity: MainActivity): Single<BaseFragment>
    fun handleOnBackPressed()
}