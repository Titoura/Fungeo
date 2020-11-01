package com.titou.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.scope.lifecycleScope


//TODO: Check if we need to override onBackPressed
class MainActivity : AppCompatActivity() {


    private val presenter: ActivityPresenter by lifecycleScope.inject()
    lateinit var frame: FrameLayout
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()

        bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            presenter.handleOnBottomMenuItemSelected(item.itemId).subscribe { fragmentClass ->
                replaceFragment(fragmentClass)
            }

            //We consumed the event
            true
        }


    }

    //TODO: add butterknife if UI binding becomes to complex
    fun bindViews() {
        frame = findViewById(R.id.frame)
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
    }


    fun replaceFragment(fragment: Class<out Fragment>) {
        val newFragment = fragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, newFragment)
            .addToBackStack(newFragment.tag)
            .commit()
    }

}