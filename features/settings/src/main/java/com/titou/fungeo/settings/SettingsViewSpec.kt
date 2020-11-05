package com.titou.fungeo.settings

import android.graphics.Typeface
import android.text.Layout
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.Text
import com.facebook.litho.widget.TextInput
import com.facebook.litho.widget.VerticalGravity
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.facebook.yoga.YogaJustify
import com.titou.database.models.LocationWithName
import com.titou.ui.R

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
        .paddingDip(YogaEdge.VERTICAL, 24f)
        .paddingRes(YogaEdge.HORIZONTAL, R.dimen.padding_default)
        .child(
            Text.create(c).textRes(com.titou.fungeo.settings.R.string.settings)
                .textAlignment(Layout.Alignment.ALIGN_CENTER)
                .textColorRes(R.color.white)
                .marginRes(YogaEdge.TOP, R.dimen.margin_large)
                .typeface(Typeface.DEFAULT_BOLD)
                .widthPercent(100F)
                .textSizeDip(40f)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_xlarge)
        ).child(
            Text.create(c)
                .textRes(com.titou.fungeo.settings.R.string.your_current_default_location)
                .marginRes(YogaEdge.TOP, R.dimen.margin_xlarge)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_large)
                .textColorRes(R.color.white)
                .typeface(Typeface.DEFAULT_BOLD)
                .textSizeDip(20F)
        ).child(
            Row.create(c)
                .apply {
                    if (defaultLocation == null) {

                        child(
                            Text.create(c)
                                .textRes(com.titou.fungeo.settings.R.string.no_default_location)
                                .textColorRes(R.color.white)
                                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_large)
                                .verticalGravity(VerticalGravity.CENTER)
                                .marginRes(YogaEdge.HORIZONTAL, R.dimen.margin_large)
                                .textSizeDip(16f)
                        )
                    } else {
                        child(
                            Text.create(c)
                                .text("${defaultLocation.name}, ${defaultLocation.countryCode}")
                                .textColorRes(R.color.white)
                                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_large)
                                .verticalGravity(VerticalGravity.CENTER)
                                .marginRes(YogaEdge.HORIZONTAL, R.dimen.margin_large)
                                .textSizeDip(16f)
                        )
                        child(
                            Image
                                .create(c)
                                .drawableRes(com.titou.fungeo.settings.R.drawable.ic_clear)
                                .paddingDip(YogaEdge.HORIZONTAL, 16F)
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
                .textRes(com.titou.fungeo.settings.R.string.set_a_default_location)
                .marginRes(YogaEdge.TOP, R.dimen.margin_xlarge)
                .marginRes(YogaEdge.BOTTOM, R.dimen.margin_large)
                .textColorRes(R.color.white)
                .typeface(Typeface.DEFAULT_BOLD)
                .textSizeDip(20F)
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