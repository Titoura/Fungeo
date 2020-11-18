package com.fungeo.presentation.locations

import android.graphics.Typeface
import android.text.TextUtils
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateInitialState
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.OnUpdateState
import com.facebook.litho.annotations.Prop
import com.facebook.litho.annotations.State
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.fungeo.data.entity.HourlyWeather
import com.fungeo.presentation.R
import com.fungeo.presentation.getIconRes
import com.fungeo.presentation.toCelsius
import com.fungeo.presentation.ui.components.WeatherWithTime

/**
 * Component which toggles between a maximum and minimum height when clicked, starting from
 * an initial height.
 */
@LayoutSpec
object LocationWeatherItemSpec {

    @OnCreateInitialState
    fun onCreateState(
        c: ComponentContext,
        isExpanded: StateValue<Boolean>
    ) {
        isExpanded.set(false)
    }

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @State isExpanded : Boolean,
        @Prop locationWithNameAndWeather: LocationWithNameAndWeather
    ): Component {
        return Column.create(c)
            .paddingRes(YogaEdge.BOTTOM, R.dimen.padding_default)
            .paddingRes(YogaEdge.TOP, R.dimen.padding_large)
            .child(
                Row.create(c).widthPercent(100f)
                    .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_large)
                    .clickHandler(LocationWeatherItem.onRowClicked(c))
                    .justifyContent(YogaJustify.SPACE_BETWEEN)
                    .child(
                        Text.create(c)
                            .text("${locationWithNameAndWeather.locationWithName.name}, ${locationWithNameAndWeather.locationWithName.countryCode}")
                            .textColorRes(
                                R.color.white
                            )
                            .verticalGravity(VerticalGravity.CENTER)
                            .ellipsize(TextUtils.TruncateAt.END)
                            .customEllipsisText(".")
                            .typeface(Typeface.DEFAULT_BOLD)
                            .isSingleLine(true)
                            .textSizeRes(R.dimen.p1)

                    )
                    .child(
                        Row.create(c).alignItems(YogaAlign.CENTER)
                            .justifyContent(YogaJustify.FLEX_END)
                            .widthPercent(50f)

                            .child(
                                Text.create(c)
                                    .verticalGravity(VerticalGravity.CENTER)
                                    .text("${locationWithNameAndWeather.weather.weatherForecast[0].temperature?.day?.toCelsius()}°C / ${locationWithNameAndWeather.weather.weatherForecast[0].temperature?.night?.toCelsius()}°C")
                                    .textColorRes(
                                        R.color.white
                                    )
                                    .widthDip(88f)
                                    .marginRes(YogaEdge.RIGHT, R.dimen.margin_large)
                                    .textSizeRes(R.dimen.p2)


                            )
                            .child(
                                Image.create(c).drawableRes(

                                    locationWithNameAndWeather.weather.currentWeather?.getIconRes()
                                        ?: R.drawable.ic_cloud
                                )
                                    .heightRes(R.dimen.daily_weather_icon_size)
                                    .widthRes(R.dimen.daily_weather_icon_size)
                            )
                            .child(
                                Image.create(c).drawableRes(R.drawable.ic_minus)
                                    .marginRes(YogaEdge.LEFT, R.dimen.margin_default)
                                    .clickHandler(LocationWeatherItem.onRemoveClick(c))
                                    .heightRes(R.dimen.minus_icon_size)
                                    .widthRes(R.dimen.minus_icon_size)
                            )
                    )
            )
            .apply {
                if(isExpanded) {
                    child(
                        Row.create(c)
                            .paddingRes(YogaEdge.HORIZONTAL, R.dimen.margin_large)
                            .justifyContent(YogaJustify.SPACE_EVENLY).apply {
                                for (hourlyWeather in getFiveNextHours(locationWithNameAndWeather.weather.hourlyWeatherForecast)) {
                                    child(
                                        WeatherWithTime.create(c).hourlyWeather(hourlyWeather)
                                    )
                                }
                            }
                    )
                }
            }

            .build()
    }

    @OnEvent(ClickEvent::class)
    fun onRemoveClick(
        c: ComponentContext,
        @Prop handleOnRemoveClick: () -> Unit
    ) {
        handleOnRemoveClick()
    }

    @OnEvent(ClickEvent::class)
    fun onRowClicked(
        c: ComponentContext
    ) {
        LocationWeatherItem.onUpdateExpanded(c)
    }

    @OnUpdateState
    fun onUpdateExpanded(
        isExpanded: StateValue<Boolean>
    ) {
        isExpanded.set(isExpanded.get()?.not())
    }

}


fun getFiveNextHours(hourlyWeathersList: List<HourlyWeather>?): List<HourlyWeather> {
    if (hourlyWeathersList == null) return emptyList()
    val fiveNextTimes = listOf(3, 7, 11, 15, 19, 23)
    return fiveNextTimes.map { hourlyWeathersList.getOrNull(it) }.requireNoNulls()
}