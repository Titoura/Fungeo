package com.fungeo.presentation.locations

import android.graphics.Typeface
import android.text.Layout
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.fungeo.presentation.R
import com.fungeo.presentation.ui.components.AnimationView

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
        .paddingRes(YogaEdge.VERTICAL, R.dimen.padding_xlarge)
        .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_default)
        .child(
            Text.create(c).textRes(R.string.your_locations)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                .textColorRes(R.color.white)
                .marginRes(YogaEdge.TOP, R.dimen.margin_large)
                .typeface(Typeface.DEFAULT_BOLD)
                .widthPercent(100F)
                .textSizeRes(R.dimen.h1)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_xlarge)
        )

        .apply {
            if (props.loading) {
                child(
                    Column.create(c).flex(1F).alignContent(YogaAlign.CENTER)
                        .justifyContent(YogaJustify.CENTER).child(
                            AnimationView.create(c).rawRes(R.raw.loader_animation)
                                .alignSelf(YogaAlign.CENTER).widthRes(R.dimen.loader_size)
                                .heightRes(R.dimen.loader_size)
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