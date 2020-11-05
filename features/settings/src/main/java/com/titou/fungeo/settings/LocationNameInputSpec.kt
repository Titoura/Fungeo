package com.titou.fungeo.settings

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.widget.ImageView
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Image
import com.facebook.litho.widget.TextChangedEvent
import com.facebook.litho.widget.TextInput
import com.facebook.yoga.YogaAlign
import com.facebook.yoga.YogaEdge
import com.titou.fungeo.core.ui.views.LoaderSuccessFailView
import com.titou.fungeo.core.ui.views.LoaderSuccessFailViewSpec

/**
 * Component which toggles between a maximum and minimum height when clicked, starting from
 * an initial height.
 */
@LayoutSpec
object LocationNameInputSpec {

    private const val inputKey: String = "input"

    @PropDefault
    val initialValue = ""

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @State text: String

    ): Component {

        val textInputHandle = Handle()

        return Row
            .create(c)

            .child(
                Row.create(c)
                    .marginRes(YogaEdge.RIGHT, R.dimen.margin_large)
                    .alignItems(YogaAlign.CENTER)
                    .backgroundRes(R.drawable.input_background)
                    .flex(1F)
                    .child(
                        TextInput
                            .create(c)
                            .key(inputKey)
                            .marginRes(YogaEdge.LEFT, R.dimen.margin_default)
                            .hintRes(R.string.add_your_location)
                            .inputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                            .let {
                                if (text.isNotEmpty()) it else
                                    it.paddingDip(YogaEdge.RIGHT, 16f)
                            }
                            .flex(1F)
                            .hintColorStateList(ColorStateList.valueOf(c.getColor(R.color.white_60)))
                            .cursorDrawableRes(R.drawable.cursor)
                            .textColorStateList(ColorStateList.valueOf(c.getColor(R.color.white)))
                            .handle(textInputHandle)
                            .textChangedEventHandler(
                                LocationNameInput.onTextInputChanged(
                                    c
                                )
                            )
                            .backgroundColor(Color.TRANSPARENT)
                            .build()

                    )
                    .let {
                        if (text.isEmpty()) it else
                            it
                                .child(
                                    Image
                                        .create(c)
                                        .drawableRes(R.drawable.ic_clear)
                                        .paddingDip(YogaEdge.HORIZONTAL, 16F)
                                        .clickHandler(LocationNameInput.onClearClick(c, inputKey))
                                )
                    }
            )
            .child(
                Image.create(c)
                    .marginRes(YogaEdge.LEFT, R.dimen.margin_large)
                    .widthRes(R.dimen.daily_weather_icon_size)
                    .heightRes(R.dimen.daily_weather_icon_size)
                    .clickHandler(LocationNameInput.onTextValidated(c, text, inputKey))
                    .scaleType(ImageView.ScaleType.FIT_CENTER)
                    .alpha(1F)
                    .drawableRes(R.drawable.ic_check)


            )
            .build()
    }

    @OnCreateInitialState
    fun onCreateState(
        c: ComponentContext,
        text: StateValue<String>,
        @Prop(optional = true) initialValue: String
    ) {
        text.set(initialValue)
    }

    @OnUpdateState
    fun onUpdateState(
        text: StateValue<String>,
        @Param input: String
    ) {
        text.set(input)
    }

    @OnEvent(TextChangedEvent::class)
    fun onTextInputChanged(
        c: ComponentContext,
        @FromEvent text: String
    ) {
        LocationNameInput.onUpdateState(c, text)
    }

    @OnEvent(ClickEvent::class)
    fun onClearClick(
        c: ComponentContext?,
        @Param key: String?
    ) {
        TextInput.setText(c, key, "")
    }

    @OnEvent(ClickEvent::class)
    fun onTextValidated(
        c: ComponentContext?,
        @Param text: String,
        @Prop onTextValidated: (String) -> Unit,
        @Param key: String?
    ) {
        onTextValidated(text)
        TextInput.setText(c, key, "")
    }

}