package com.titou.urgo.locations

import android.graphics.Typeface
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
import com.titou.fungeo.core.ui.views.toCelsius

/**
 * Component which toggles between a maximum and minimum height when clicked, starting from
 * an initial height.
 */
@LayoutSpec
object LocationWeatherItemSpec {

    @OnCreateInitialState
    fun onCreateInitialState(
        c: ComponentContext,
        @Prop initialHeight: Float,
        currentHeight: StateValue<Float>
    ) {
        currentHeight.set(initialHeight)
    }

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop locationWithNameAndWeather: LocationWithNameAndWeather,
        @State currentHeight: Float
    ): Component {
        return Row.create(c).widthPercent(100f).alignItems(YogaAlign.CENTER)
            .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_large)
            .paddingRes(YogaEdge.VERTICAL, R.dimen.padding_small).child(
                Text.create(c).text(locationWithNameAndWeather.locationName)
                    .textColorRes(
                        com.titou.ui.R.color.white
                    )
                    .verticalGravity(VerticalGravity.CENTER)
                    .typeface(Typeface.DEFAULT_BOLD)
                    .flex(1F).textSizeDip(24F)

            )
            .child(
                Text.create(c)
                    .verticalGravity(VerticalGravity.CENTER)
                    .text("${locationWithNameAndWeather.weather.weatherForecast[0].temperature?.day?.toCelsius()}°C / ${locationWithNameAndWeather.weather.weatherForecast[0].temperature?.night?.toCelsius()}°C")
                    .textColorRes(
                        com.titou.ui.R.color.white
                    )
                    .marginRes(YogaEdge.RIGHT, R.dimen.margin_xlarge)
                    .textSizeDip(16F)


            )
            .child(
                Image.create(c).drawableRes(locationWithNameAndWeather.weather.currentWeather?.getIconRes()?: com.titou.ui.R.drawable.ic_cloud)
                    .heightRes(R.dimen.daily_weather_icon_size)
                    .widthRes(R.dimen.daily_weather_icon_size)
            )
//            ).child(
//                Row.create(c).child(
//                    Text.create(c).text(dailyWeather.)
//                )
//                    .child(
//                        Text.create(c)
//                            .text("${dailyWeather.temperature?.day}/${dailyWeather.temperature?.night}")
//                    )
//                    .child(
//                        Image.create(c).drawableRes(R.drawable.ic_sun)
//                    )

            .clickHandler(LocationWeatherItem.onClick(c))
            .build()
    }

    @OnUpdateState
    fun onUpdateState(
        currentHeight: StateValue<Float>,
        @Param collapse: Float,
        @Param expand: Float
    ) {
        val currentState = currentHeight.get()
        if (currentState != null) {
            if (currentState < expand) {
                currentHeight.set(expand)
            } else {
                currentHeight.set(collapse)
            }
        }
    }

    @OnEvent(ClickEvent::class)
    fun onClick(
        c: ComponentContext,
        @Prop collapseHeight: Float,
        @Prop expandHeight: Float
    ) {
        LocationWeatherItem.onUpdateState(c, collapseHeight, expandHeight)
    }
}