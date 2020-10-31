package com.titou.home

import com.facebook.litho.Column
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout

@LayoutSpec
internal object HomeViewSpec {

    @OnCreateLayout
    fun onCreateLayout(
        c: ComponentContext

    ) = Column
        .create(c)
        .flex(1F)
        .build()

}
