package com.fungeo.presentation.home

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.fungeo.data.entity.Weather
import com.fungeo.presentation.R
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope


class HomeFragment(parentActivity: AppCompatActivity) : Fragment() {

    private val presenter: HomePresenter by inject()

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
            createView(true, null, getString(R.string.unknown))
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
        propDisposable.dispose()
        super.onDestroy()
    }
}

data class Props(
    val weather: Weather?,
    val location: Location?,
    val locationName: String
)
