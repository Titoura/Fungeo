package com.titou.urgo.locations

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

/**
 * This component renders a horizontal list with items of various heights, which can adapt height
 * to always accommodate the height of the tallest item. If the height of the h-scroll is already
 * taller than the highest item it will not shrink to fit.
 * Measuring the height this way is extremely inefficient.
 */
@LayoutSpec
object LocationsWeatherListSpec {

    @OnCreateLayout
    fun onCreateLayout(c: ComponentContext, @Prop weatherList: List<LocationWithNameAndWeather>): Component {

        return RecyclerCollectionComponent.create(c)
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
                DataDiffSection.create<LocationWithNameAndWeather>(SectionContext(c))
                    .data(weatherList)
                    .renderEventHandler(LocationsWeatherList.onRender(c))
                    .build()
            )
            .canMeasureRecycler(true)
            .build()
    }

    @OnEvent(RenderEvent::class)
    fun onRender(c: ComponentContext, @FromEvent model: LocationWithNameAndWeather, @FromEvent index: Int, @Prop onRemoveClick : (LocationWithNameAndWeather) -> Unit): RenderInfo {
        return ComponentRenderInfo.create()
            .component(
                LocationWeatherItem.create(c)
                    .locationWithNameAndWeather(model)
                    .handleOnRemoveClick{onRemoveClick(model)}
                    .widthPercent(100F)
            )
            .build()
    }



}