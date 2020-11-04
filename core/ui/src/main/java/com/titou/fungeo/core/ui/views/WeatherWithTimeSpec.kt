package com.titou.fungeo.core.ui.views

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
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.titou.database.models.HourlyWeather
import com.titou.database.models.Weather
import com.titou.fungeo.core.ui.views.AnimationView
import com.titou.ui.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

@LayoutSpec
internal object WeatherWithTimeSpec {

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop hourlyWeather: HourlyWeather?

    ): Component = Column
        .create(c)
        .paddingRes(YogaEdge.ALL, R.dimen.margin_default)
        .child(
            Text.create(c)
                .text("${hourlyWeather?.temperature?.toCelsius().toString()}°C")
                .marginRes(YogaEdge.TOP, R.dimen.margin_default)
                .textColorRes(R.color.white)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                .textSizeDip(16f)
                .typeface(Typeface.DEFAULT_BOLD)
        )
        .child(
            Image.create(c).drawableRes(hourlyWeather?.getIconRes()?:R.drawable.ic_cloud)
                .marginRes(YogaEdge.VERTICAL, R.dimen.margin_default)

        )
        .child(
            Text.create(c)
                .text("${hourlyWeather?.dateTime?.format(DateTimeFormatter.ofPattern("HH"))}h")
                .textColorRes(R.color.white)
                .textSizeDip(16f)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
        ).child(
            Text.create(c)
                .text(
                    let {
                        val dayDifference = LocalDateTime.now().toLocalDate()
                            .until(hourlyWeather?.dateTime?.toLocalDate(), ChronoUnit.DAYS)
                        if (dayDifference > 0)
                            "+${dayDifference}"
                        else
                            " "
                    }
                )
                .textColorRes(R.color.white)
                .textSizeDip(12f)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
        )

        .build()

}
