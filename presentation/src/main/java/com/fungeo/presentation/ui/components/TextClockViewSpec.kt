package com.fungeo.presentation.ui.components

import android.content.Context
import android.view.View
import android.widget.TextClock
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.MountSpec
import com.facebook.litho.annotations.OnCreateMountContent
import com.facebook.litho.annotations.OnMount
import com.facebook.litho.annotations.Prop
import com.fungeo.presentation.R

@MountSpec
object TextClockViewSpec {

    @OnCreateMountContent
    fun onCreateMountContent(c: Context) = TextClock(c)

    @OnMount
    fun onMount(
        c: ComponentContext,
        textClock: TextClock,
        @Prop textColorRes: Int
    ) {
        textClock.setTextColor(c.getColor(textColorRes))
        textClock.textSize = c.resources.getDimension(R.dimen.h2)
        textClock.textAlignment = View.TEXT_ALIGNMENT_CENTER
    }
}
