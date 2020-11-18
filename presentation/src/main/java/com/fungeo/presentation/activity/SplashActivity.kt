package com.fungeo.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.fungeo.presentation.R


//TODO: Check if we need to override onBackPressed
class SplashActivity : AppCompatActivity() {


    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH = 600L

    /** Called when the com.fungeo.presentation.activity is first created.
     * *
     */
    override fun onCreate(icicle: Bundle?)
    {
        super.onCreate(icicle)
        setContentView(R.layout.splash_activity)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}