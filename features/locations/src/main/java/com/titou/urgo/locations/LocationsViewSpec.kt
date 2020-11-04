package com.titou.urgo.locations

import android.graphics.Typeface
import android.text.Layout
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.titou.fungeo.core.ui.views.*
import com.titou.ui.R

@LayoutSpec
internal object LocationsViewSpec {


    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop onTextValidated: (String) -> Unit,
        @Prop handleOnRemoveClick: (LocationWithNameAndWeather) -> Unit,
        @Prop props: Props

    ): Component = Column
        .create(c)
        .flex(1F)
        .paddingDip(YogaEdge.VERTICAL, 24f)
        .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_default)
        .child(
            Text.create(c).textRes(com.titou.urgo.locations.R.string.your_locations)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                .textColorRes(R.color.white)
                .marginRes(YogaEdge.TOP, R.dimen.margin_large)
                .typeface(Typeface.DEFAULT_BOLD)
                .widthPercent(100F)
                .textSizeDip(40f)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_xlarge)
        )

        .apply {
            if (props.loading) {
                child(
                    Column.create(c).flex(1F).alignContent(YogaAlign.CENTER)
                        .justifyContent(YogaJustify.CENTER).child(
                            AnimationView.create(c).rawRes(R.raw.loader_animation)
                                .alignSelf(YogaAlign.CENTER).widthDip(200f).heightDip(200f)
                        )
                )
            } else {
                child(
                    LocationsWeatherList.create(c).weatherList(props.locationsWithNameAndWeathers)
                        .onRemoveClick { locationWithWeather ->
                            handleOnRemoveClick(locationWithWeather)
                        }

                )
                if (props.locationsWithNameAndWeathers.size <= 7) {
                    child(
                        LocationNameInput.create(c)
                            .marginRes(YogaEdge.RIGHT, R.dimen.margin_large)
                            .key(props.locationsWithNameAndWeathers.size.toString())
                            .marginRes(YogaEdge.TOP, R.dimen.margin_xlarge)
                            .onTextValidated { locationName -> onTextValidated(locationName) }

                    )
                }

            }
        }
        .build()


}