package com.titou.home

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
internal object HomeViewSpec {

    val colors =
        listOf(
            Color.BLACK, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED, Color.MAGENTA
        )

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop loading: Boolean,
        @Prop weather: Weather?,
        @Prop locationName: String?

    ): Component = Column
        .create(c)
        .flex(1F)
        .paddingRes(YogaEdge.VERTICAL, R.dimen.padding_xlarge)
        .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_default)
        .apply {
            if (loading) {
                child(
                    Column.create(c).heightPercent(50F).alignContent(YogaAlign.CENTER)
                        .justifyContent(YogaJustify.CENTER).child(
                            AnimationView.create(c).rawRes(R.raw.loader_animation)
                                .alignSelf(YogaAlign.CENTER)
                                .widthRes(com.titou.home.R.dimen.loader_size)
                                .widthRes(com.titou.home.R.dimen.loader_size)
                        )
                )
                child(
                    Text.create(c).textRes(com.titou.home.R.string.loading_msg)
                        .alignSelf(YogaAlign.CENTER)
                        .textAlignment(Layout.Alignment.ALIGN_CENTER)
                        .textColorRes(R.color.white).heightPercent(50F)
                        .verticalGravity(VerticalGravity.CENTER)
                        .marginRes(YogaEdge.ALL, R.dimen.margin_xlarge)
                )
            } else {
                child(
                    Text.create(c).text(locationName)
                        .verticalGravity(VerticalGravity.CENTER)
                        .textAlignment(Layout.Alignment.ALIGN_CENTER)
                        .textColorRes(R.color.white)
                        .marginRes(YogaEdge.TOP, R.dimen.margin_large)
                        .typeface(Typeface.DEFAULT_BOLD)
                        .textSizeRes(R.dimen.h1)
                )

                child(
                    Image.create(c).drawableRes(
                        weather?.currentWeather?.getIconRes() ?: R.drawable.ic_cloud
                    )
                        .widthRes(R.dimen.icon_large)
                        .widthRes(R.dimen.icon_large)
                        .alignSelf(YogaAlign.CENTER)
                        .marginRes(YogaEdge.VERTICAL, R.dimen.margin_default)

                )
                child(
                    Text.create(c)
                        .text("${weather?.currentWeather?.temperature?.toCelsius()}°C")
                        .marginRes(YogaEdge.VERTICAL, R.dimen.margin_small)
                        .textColorRes(R.color.white)
                        .textAlignment(Layout.Alignment.ALIGN_CENTER)
                        .textSizeRes(R.dimen.h2)
                        .typeface(Typeface.DEFAULT_BOLD)
                )

                child(
                    Row.create(c).paddingRes(YogaEdge.HORIZONTAL, R.dimen.margin_large)
                        .justifyContent(YogaJustify.SPACE_EVENLY).apply {
                            for (hourlyWeather in getFiveNextHours(weather?.hourlyWeatherForecast)) {
                                child(
                                    WeatherWithTime.create(c).hourlyWeather(hourlyWeather)
                                )
                            }
                        }
                )
                    .child(
                        Column.create(c).flex(1F)
                            .marginRes(YogaEdge.TOP, R.dimen.margin_large).child(
                                DailyWeatherList.create(c).dailyWeatherList(
                                    weather?.weatherForecast?.subList(1, 7) ?: emptyList()
                                )
                            )

                    )
            }
        }

        .build()


}

fun getFiveNextHours(hourlyWeathersList: List<HourlyWeather>?): List<HourlyWeather> {
    if (hourlyWeathersList == null) return emptyList()
    val fiveNextTimes = listOf(3, 7, 11, 15, 19, 23)
    return fiveNextTimes.map { hourlyWeathersList.getOrNull(it) }.requireNoNulls()
}