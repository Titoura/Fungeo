package com.titou.fungeo.core.ui.views

import android.content.Context
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.MountSpec
import com.facebook.litho.annotations.OnCreateMountContent
import com.facebook.litho.annotations.OnMount
import com.facebook.litho.annotations.Prop

@MountSpec
object AnimationViewSpec {

    @OnCreateMountContent
    fun onCreateMountContent(c: Context) = LottieAnimationView(c)

    @OnMount
    fun onMount(
        c: ComponentContext,
        animationView: LottieAnimationView,
        @Prop rawRes: Int
    ) {
        animationView.setAnimation(rawRes)
        animationView.playAnimation()
        animationView.repeatMode = LottieDrawable.RESTART
        animationView.repeatCount = LottieDrawable.INFINITE
    }
}
