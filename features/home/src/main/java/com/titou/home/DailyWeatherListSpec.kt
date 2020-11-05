package com.titou.home

import android.view.View
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop

import com.facebook.litho.annotations.FromEvent
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.common.DataDiffSection
import com.facebook.litho.sections.common.RenderEvent
import com.facebook.litho.sections.widget.ListRecyclerConfiguration
import com.facebook.litho.sections.widget.RecyclerBinderConfiguration
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import com.facebook.litho.widget.ComponentRenderInfo
import com.facebook.litho.widget.RenderInfo
import com.titou.database.models.DatedWeatherForecast

/**
 * This component renders a horizontal list with items of various heights, which can adapt height
 * to always accommodate the height of the tallest item. If the height of the h-scroll is already
 * taller than the highest item it will not shrink to fit.
 * Measuring the height this way is extremely inefficient.
 */
@LayoutSpec
object DailyWeatherListSpec {

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop dailyWeatherList: List<DatedWeatherForecast>): Component {

        return RecyclerCollectionComponent.create(c).overScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS).disablePTR(true)
            .recyclerConfiguration(
                ListRecyclerConfiguration.create()
                    .recyclerBinderConfiguration(
                        RecyclerBinderConfiguration.create()
                            .hasDynamicItemHeight(true) // This enables dynamic height measurement.
                            .wrapContent(true)
                            .build()
                    ).build()
            )
            .section(
                DataDiffSection.create<DatedWeatherForecast>(SectionContext(c))
                    .data(dailyWeatherList)
                    .renderEventHandler(DailyWeatherList.onRender(c))
                    .build()
            )
            .canMeasureRecycler(true)
            .build()
    }

    @OnEvent(RenderEvent::class)
    fun onRender(c: ComponentContext, @FromEvent model: DatedWeatherForecast, @FromEvent index: Int): RenderInfo {
        return ComponentRenderInfo.create()
            .component(
                DailyWeatherItem.create(c)
                    .dailyWeather(model)
                    .widthPercent(100F)
                    .collapseHeight(50f)
                    .expandHeight(250f)
                    .initialHeight(50f)
                    .widthDip(100f)
            )
            .build()
    }
}