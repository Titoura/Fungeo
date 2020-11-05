package com.titou.urgo.locations

import android.graphics.Typeface
import android.text.TextUtils
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateInitialState
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.OnUpdateState
import com.facebook.litho.annotations.Param
import com.facebook.litho.annotations.Prop
import com.facebook.litho.annotations.State
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.titou.fungeo.core.ui.views.toCelsius

/**
 * Component which toggles between a maximum and minimum height when clicked, starting from
 * an initial height.
 */
@LayoutSpec
object LocationWeatherItemSpec {

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop locationWithNameAndWeather: LocationWithNameAndWeather
    ): Component {
        return Row.create(c).widthPercent(100f)
            .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_large)
            .justifyContent(YogaJustify.SPACE_BETWEEN)
            .paddingRes(YogaEdge.VERTICAL, R.dimen.padding_default).child(
                Text.create(c)
                    .text("${locationWithNameAndWeather.locationWithName.name}, ${locationWithNameAndWeather.locationWithName.countryCode}")
                    .textColorRes(
                        com.titou.ui.R.color.white
                    )
                    .verticalGravity(VerticalGravity.CENTER)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .customEllipsisText(".")
                    .typeface(Typeface.DEFAULT_BOLD)
                    .isSingleLine(true)
                    .textSizeRes(com.titou.ui.R.dimen.p1)

            )
            .child(
                Row.create(c).alignItems(YogaAlign.CENTER).justifyContent(YogaJustify.FLEX_END).widthPercent(50f)

                    .child(
                        Text.create(c)
                            .verticalGravity(VerticalGravity.CENTER)
                            .text("${locationWithNameAndWeather.weather.weatherForecast[0].temperature?.day?.toCelsius()}°C / ${locationWithNameAndWeather.weather.weatherForecast[0].temperature?.night?.toCelsius()}°C")
                            .textColorRes(
                                com.titou.ui.R.color.white
                            )
                            .widthDip(88f)
                            .marginRes(YogaEdge.RIGHT, R.dimen.margin_large)
                            .textSizeRes(R.dimen.p2)


                    )
                    .child(
                        Image.create(c).drawableRes(
                            locationWithNameAndWeather.weather.currentWeather?.getIconRes()
                                ?: com.titou.ui.R.drawable.ic_cloud
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
            .build()
    }

    @OnEvent(ClickEvent::class)
    fun onRemoveClick(
        c: ComponentContext,
        @Prop handleOnRemoveClick: () -> Unit
    ) {
        handleOnRemoveClick()
    }
}