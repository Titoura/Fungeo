package com.titou.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.titou.database.models.Weather
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope
import java.security.Permission


class HomeFragment(parentActivity: AppCompatActivity) : Fragment() {

    private val presenter: HomePresenter by lifecycleScope.inject()

    private lateinit var propDisposable: Disposable

    init {
        lifecycleScope.linkTo(parentActivity.lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val c = ComponentContext(context)

        val createView =
            { loading: Boolean, weather: Weather?, locationName: String ->
                HomeView
                    .create(c)
                    .loading(loading)
                    .weather(weather)
                    .locationName(locationName)
            }

        val lithoView = LithoView.create(
            c,
            createView(true, null, "Test")
                .build()
        )


        propDisposable = presenter
            .props()
            .subscribe({
                lithoView.setComponentAsync(
                    createView(
                        false, it.weather, it.locationName
                    ).build()
                )
            }, ::onError)!!

        return lithoView
    }

    private fun onError(throwable: Throwable) {
        Log.e(tag, "error: ${throwable.localizedMessage}")
        throwable.printStackTrace()
    }

    override fun onDestroy() {
        propDisposable?.dispose()
        super.onDestroy()
    }
}

data class Props(
    val weather: Weather?,
    val location: Location?,
    val locationName: String
)
