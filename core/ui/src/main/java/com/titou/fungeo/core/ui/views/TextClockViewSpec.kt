package com.titou.fungeo.core.ui.views

import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.view.View
import android.widget.TextClock
import androidx.core.graphics.drawable.toDrawable
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.TextAlignment
import com.titou.ui.R
import java.text.DateFormat
import java.time.format.DateTimeFormatter

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
