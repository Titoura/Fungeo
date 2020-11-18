package com.fungeo.presentation.locations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.fungeo.data.entity.LocationWithName
import com.fungeo.data.entity.Weather
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.scope.lifecycleScope


class LocationsFragment(parentActivity: AppCompatActivity) : Fragment() {

    private val presenter: LocationsPresenter by lifecycleScope.inject()

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
            {  props : Props ->
                LocationsView
                    .create(c)
                    .onTextValidated{locationName -> presenter.handleOnTextValidated(locationName)}
                    .handleOnRemoveClick{locationWithName -> presenter.handleOnRemoveClick(locationWithName.locationWithName)}
                    .props(props)
            }

        val lithoView = LithoView.create(
            c,
            createView(Props(emptyList(), true))
                .build()
        )


        propDisposable = presenter
            .props()
            .subscribe({
                lithoView.setComponentAsync(
                    createView(
                         it
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

data class LocationWithNameAndWeather(
    val weather: Weather,
    val locationWithName: LocationWithName
)

data class Props(
    val locationsWithNameAndWeathers: List<LocationWithNameAndWeather>,
    val loading : Boolean
)
