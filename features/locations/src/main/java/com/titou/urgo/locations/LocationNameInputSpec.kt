package com.titou.urgo.locations

import android.content.res.ColorStateList
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.widget.ImageView
import com.facebook.litho.*
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.*
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
    val inputType = InputType.TYPE_CLASS_TEXT

    @PropDefault
    val initialValue = ""

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext,
        @State text: String,
        @State isLoadingVisible: Boolean,
        @Prop(optional = true) inputType: Int,
        @Prop(optional = true) initialValue: String,
        @Prop(optional = true) inputFilters: List<InputFilter>?

    ): Component {

        val textInputHandle = Handle()

        return Row
            .create(c)
            .paddingDip(YogaEdge.LEFT, 16f)
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
                            .hintRes(R.string.add_a_location)
                            .inputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS)
                            .initialText(initialValue)
                            .inputFilters(inputFilters ?: emptyList())
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
                if (text.isEmpty()) {
                    Image.create(c).widthDip(40f)
                        .heightDip(40f)
                        .alpha(0.6F)
                        .drawableRes(R.drawable.ic_plus)
                } else if (isLoadingVisible) {
                    LoaderSuccessFailView.create(c)
                        .rawRes(com.titou.ui.R.raw.loading_success_fail_spinner)
                        .status(
                            if (isLoadingVisible)
                                LoaderSuccessFailViewSpec.Status.LOADING
                            else
                                LoaderSuccessFailViewSpec.Status.SUCCESS
                        )
                        .widthDip(40f).heightDip(40f)
                        .hideLoader { isVisible ->
                            LocationNameInput.onUpdateLoader(
                                c,
                                isVisible
                            )
                        }
                } else {
                    Image.create(c)
                        .marginRes(YogaEdge.LEFT, R.dimen.margin_large)
                        .widthRes(R.dimen.daily_weather_icon_size)
                        .heightRes(R.dimen.daily_weather_icon_size)
                        .clickHandler(LocationNameInput.onTextValidated(c, text))
                        .scaleType(ImageView.ScaleType.FIT_CENTER)
                        .alpha(1F)
                        .drawableRes(R.drawable.ic_plus)
                }

            )
            .build()
    }

    @OnCreateInitialState
    fun onCreateState(
        c: ComponentContext,
        text: StateValue<String>,
        isLoadingVisible: StateValue<Boolean>,
        @Prop(optional = true) initialValue: String
    ) {
        text.set(initialValue)
        isLoadingVisible.set(false)
    }

    @OnUpdateState
    fun onUpdateState(
        text: StateValue<String>,
        @Param input: String
    ) {
        text.set(input)
    }

    @OnUpdateState
    fun onUpdateLoader(
        isLoadingVisible: StateValue<Boolean>,
        @Param newIsLoadingVisible: Boolean
    ) {
        isLoadingVisible.set(newIsLoadingVisible)
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
        @Prop onTextValidated : (String) -> Unit
    ) {
        onTextValidated(text)
        LocationNameInput.onUpdateLoader(c, true)
    }

}