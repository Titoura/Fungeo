package com.fungeo.presentation.settings

import android.graphics.Typeface
import android.text.Layout
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.fungeo.data.entity.LocationWithName
import com.fungeo.presentation.R

@LayoutSpec
internal object SettingsViewSpec {


    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @Prop onTextValidated: (String) -> Unit,
        @Prop defaultLocation: LocationWithName?

    ): Component = Column
        .create(c)
        .flex(1F)
        .paddingRes(YogaEdge.VERTICAL, R.dimen.padding_xlarge)
        .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_default)
        .child(
            Text.create(c).textRes(R.string.settings)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                .textColorRes(R.color.white)
                .marginRes(YogaEdge.TOP, R.dimen.margin_large)
                .typeface(Typeface.DEFAULT_BOLD)
                .widthPercent(100F)
                .textSizeRes(R.dimen.h1)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_xlarge)
        ).child(
            Text.create(c)
                .textRes(R.string.your_current_default_location)
                .marginRes(YogaEdge.TOP, R.dimen.margin_xlarge)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_large)
                .textColorRes(R.color.white)
                .typeface(Typeface.DEFAULT_BOLD)
                .textSizeRes(R.dimen.p1)
        ).child(
            Row.create(c)
                .alignItems(YogaAlign.CENTER)
                .apply {
                    if (defaultLocation == null) {

                        child(
                            Text.create(c)
                                .textRes(R.string.no_default_location)
                                .textColorRes(R.color.white)
                                .verticalGravity(VerticalGravity.CENTER)
                                .marginRes(YogaEdge.HORIZONTAL, R.dimen.margin_large)
                                .textSizeRes(R.dimen.p2)
                        )
                    } else {
                        child(
                            Text.create(c)
                                .text("${defaultLocation.name}, ${defaultLocation.countryCode}")
                                .textColorRes(R.color.white)
                                .verticalGravity(VerticalGravity.CENTER)
                                .marginRes(YogaEdge.HORIZONTAL, R.dimen.margin_large)
                                .textSizeRes(R.dimen.p2)
                        )
                        child(
                            Image
                                .create(c)
                                .drawableRes(R.drawable.ic_clear)
                                .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_default)
                                .clickHandler(
                                    SettingsView.onRemoveClick(
                                        c
                                    )
                                )
                        )

                    }
                }
        )
        .child(
            Text.create(c)
                .textRes(R.string.set_a_default_location)
                .marginRes(YogaEdge.TOP, R.dimen.margin_xlarge)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_large)
                .textColorRes(R.color.white)
                .typeface(Typeface.DEFAULT_BOLD)
                .textSizeRes(R.dimen.p1)
        )
        .child(
            LocationNameInput.create(c)
                .marginRes(YogaEdge.HORIZONTAL, R.dimen.margin_large)
                .marginRes(YogaEdge.TOP, R.dimen.margin_large)
                .onTextValidated { locationName -> onTextValidated(locationName) }

        )


        .build()


    @OnEvent(ClickEvent::class)
    fun onRemoveClick(
        c: ComponentContext?,
        @Prop handleOnRemoveClick: () -> Unit
    ) {
        handleOnRemoveClick()
    }


}