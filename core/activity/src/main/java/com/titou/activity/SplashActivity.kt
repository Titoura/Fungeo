package com.titou.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity


//TODO: Check if we need to override onBackPressed
class SplashActivity : AppCompatActivity() {


    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH = 1000L

    /** Called when the activity is first created.
     * *
     */
    override fun onCreate(icicle: Bundle?)
    {
        super.onCreate(icicle)
        setContentView(com.titou.activity.R.layout.splash_activity)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/Handler().postDelayed(Runnable { /* Create an Intent that will start the Menu-Activity. */
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}