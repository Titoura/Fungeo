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
        .child(
            Text.create(c).textRes(com.titou.urgo.locations.R.string.your_locations)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                .textColorRes(R.color.white)
                .marginRes(YogaEdge.TOP, R.dimen.margin_default)
                .typeface(Typeface.DEFAULT_BOLD)
                .widthPercent(100F)
                .textSizeDip(40f)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_xlarge)
        )

        .apply {
            if (loading || weathers.isNullOrEmpty()) {
                child(
                    Column.create(c).flex(1F).alignContent(YogaAlign.CENTER)
                        .justifyContent(YogaJustify.CENTER).child(
                            AnimationView.create(c).rawRes(R.raw.loader_animation)
                                .alignSelf(YogaAlign.CENTER).widthDip(200f).heightDip(200f)
                        )
                )
            } else {
                child(
                    LocationsWeatherList.create(c).weatherList(weathers)
                )
            }
        }
        .build()


}