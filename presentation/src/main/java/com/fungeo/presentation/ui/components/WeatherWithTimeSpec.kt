package com.fungeo.presentation.ui.components

import android.graphics.Typeface
import android.text.Layout
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import com.fungeo.data.entity.HourlyWeather
import com.fungeo.presentation.R
import com.fungeo.presentation.getIconRes
import com.fungeo.presentation.toCelsius
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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
                .textSizeRes(R.dimen.p2)
                .typeface(Typeface.DEFAULT_BOLD)
        )
        .child(
            Image.create(c).drawableRes(hourlyWeather?.getIconRes() ?: R.drawable.ic_cloud)
                .widthRes(R.dimen.icon_small)
                .heightRes(R.dimen.icon_small)


        )
        .child(
            Text.create(c)
                .text("${hourlyWeather?.dateTime?.format(DateTimeFormatter.ofPattern("HH"))}h")
                .textColorRes(R.color.white)
                .textSizeRes(R.dimen.p2)
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
                .textSizeRes(R.dimen.p3)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
        )

        .build()

}
