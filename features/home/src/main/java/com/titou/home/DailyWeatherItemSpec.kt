package com.titou.home

import android.graphics.Typeface
import com.facebook.litho.*
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.titou.database.models.DatedWeatherForecast
import com.titou.fungeo.core.ui.views.toCelsius

/**
 * Component which toggles between a maximum and minimum height when clicked, starting from
 * an initial height.
 */
@LayoutSpec
object DailyWeatherItemSpec {


    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop dailyWeather: DatedWeatherForecast
    ): Component {
        return Row.create(c).widthPercent(100f).alignItems(YogaAlign.CENTER)
            .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_large)
            .paddingRes(YogaEdge.VERTICAL, R.dimen.padding_small).child(
                Text.create(c).text(dailyWeather.date.dayOfWeek.name.toLowerCase().capitalize())
                    .textColorRes(
                        com.titou.ui.R.color.white
                    )
                    .verticalGravity(VerticalGravity.CENTER)
                    .typeface(Typeface.DEFAULT_BOLD)
                    .flex(1F)                    .textSizeRes(R.dimen.h3)


            )
            .child(
                Text.create(c)
                    .verticalGravity(VerticalGravity.CENTER)
                    .text("${dailyWeather.temperature?.day?.toCelsius()}°C / ${dailyWeather.temperature?.night?.toCelsius()}°C")
                    .textColorRes(
                        com.titou.ui.R.color.white
                    )
                    .marginRes(YogaEdge.RIGHT, R.dimen.margin_xlarge)
                    .textSizeRes(R.dimen.p2)


            )
            .child(
                Image.create(c).drawableRes(dailyWeather.getIconRes())
                    .heightRes(R.dimen.daily_weather_icon_size)
                    .widthRes(R.dimen.daily_weather_icon_size)
            )
            .build()
    }

}