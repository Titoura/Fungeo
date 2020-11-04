package com.titou.urgo.locations

import android.graphics.Color
import android.graphics.Typeface
import android.text.Layout
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.VerticalGravity
import com.facebook.litho.widget.VerticalScroll
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.facebook.yoga.YogaPositionType
import com.titou.database.models.HourlyWeather
import com.titou.database.models.Weather
import com.titou.fungeo.core.ui.views.*
import com.titou.ui.R

@LayoutSpec
internal object LocationsViewSpec {

    val colors =
        listOf(
            Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED, Color.MAGENTA
        )

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop loading: Boolean,
        @Prop weathers: List<LocationWithNameAndWeather>?

    ): Component = Column
        .create(c)
        .flex(1F)
        .paddingDip(YogaEdge.VERTICAL, 24f)
        .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_default)
        .apply {
            if (!weathers.isNullOrEmpty()) {
                child(
                    LocationsWeatherList.create(c).weatherList(weathers)
                )
            }
        }
        .build()


}

fun getFiveNextHours(hourlyWeathersList: List<HourlyWeather>?): List<HourlyWeather> {
    if (hourlyWeathersList == null) return emptyList()
    val fiveNextTimes = listOf(3, 7, 11, 15, 19, 23)
    return fiveNextTimes.map { hourlyWeathersList.getOrNull(it) }.requireNoNulls() ?: emptyList()
}