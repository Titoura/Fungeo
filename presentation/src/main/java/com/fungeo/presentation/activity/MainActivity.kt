package com.fungeo.presentation.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.fungeo.presentation.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.scope.lifecycleScope


class MainActivity : AppCompatActivity() {


    private val presenter: ActivityPresenter by inject()
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
        )

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            presenter.handleOnBottomMenuItemSelected(item.itemId, this)
                .map { it }
                .subscribe { fragment ->
                    replaceFragment(fragment)
                }

            item.isChecked = true

            false
        }

        presenter.buildDeviceLocationManager(this)


    }

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
            presenter.getCurrentLocationObservable().subscribe({
                presenter.handleOnBottomMenuItemSelected(R.id.navigation_home, this)
                    .subscribe({
                        replaceFragment(it)
                    }, {
                        Log.e("MainActivity", it?.localizedMessage ?: "Error")
                    })
            }, {})
        }
    }

    override fun onBackPressed() {

        val seletedItemId = bottomNavigationView.selectedItemId
        if (R.id.navigation_home != seletedItemId) {
            bottomNavigationView.selectedItemId = R.id.navigation_home
            presenter.handleOnBottomMenuItemSelected(R.id.navigation_home, this)
                .map { it }
                .subscribe { fragment ->
                    replaceFragment(fragment)
                }
        } else {
            finish()
        }
    }


}