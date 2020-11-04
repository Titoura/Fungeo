package com.titou.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.titou.fungeo.location.LocationManager
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.scope.lifecycleScope


//TODO: Check if we need to override onBackPressed
class MainActivity : AppCompatActivity() {


    private val presenter: ActivityPresenter by lifecycleScope.inject()
    private val locationManager: LocationManager by inject()
    lateinit var frame: FrameLayout
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Koin Fragment Factory
        setupKoinFragmentFactory(lifecycleScope)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        presenter.handleOnBottomMenuItemSelected(R.id.navigation_home, this)
            .subscribe({
                replaceFragment(it)
            }, {
                Log.e("MainActivity", it?.localizedMessage ?: "Error")
            })

        bottomNavigationView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                android.R.color.transparent
            )
        );
        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            presenter.handleOnBottomMenuItemSelected(item.itemId, this)
                .map { it }
                .subscribe { fragment ->
                    replaceFragment(fragment)
                }

            item.isChecked = true

            false
        }

        locationManager.build(this)


    }

    //TODO: add butterknife if UI binding becomes to complex
    fun bindViews() {
        frame = findViewById(R.id.frame)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
    }


    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == resources.getInteger(R.integer.location_permission_request_code) && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocation()?.subscribe({
                presenter.handleOnBottomMenuItemSelected(R.id.navigation_home, this)
                    .subscribe({
                        replaceFragment(it)
                    }, {
                        Log.e("MainActivity", it?.localizedMessage ?: "Error")
                    })
            }, {})
        }
    }
}