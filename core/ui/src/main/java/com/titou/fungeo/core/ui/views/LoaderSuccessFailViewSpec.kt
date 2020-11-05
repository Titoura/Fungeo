package com.titou.fungeo.core.ui.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.text.TextUtils
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.LottieDrawable
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.MountSpec
import com.facebook.litho.annotations.OnCreateMountContent
import com.facebook.litho.annotations.OnMount
import com.facebook.litho.annotations.Prop
import java.lang.NullPointerException

@MountSpec
object LoaderSuccessFailViewSpec {

    //TODO: extract frames to res folder
    enum class Status(val minFrame: Int, val maxFrame: Int) {
        LOADING(0, 119),
        SUCCESS(119, 417),
        FAIL(703, 840)
    }

    @OnCreateMountContent
    fun onCreateMountContent(c: Context) = LottieAnimationView(c)

    @OnMount
    fun onMount(
        c: ComponentContext,
        animationView: LottieAnimationView,
        @Prop rawRes: Int,
        @Prop status: Status,
        @Prop hideLoader: (Boolean) -> Unit
    ) {
        animationView.setAnimation(rawRes)

        animationView.repeatMode = LottieDrawable.RESTART
        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.setMinFrame(status.minFrame)
        animationView.setMaxFrame(status.maxFrame)

        animationView.playAnimation()
    }
}

