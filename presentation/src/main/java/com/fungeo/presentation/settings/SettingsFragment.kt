package com.fungeo.presentation.settings

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
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.scope.lifecycleScope


class SettingsFragment(parentActivity: AppCompatActivity) : Fragment() {

    private val presenter: SettingsPresenter by lifecycleScope.inject()

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
            {  locationWithName : LocationWithName? ->
                SettingsView
                    .create(c)
                    .onTextValidated{locationName -> presenter.setDefaultLocation(locationName)}
                    .handleOnRemoveClick{presenter.handleOnRemoveClick()}
                    .defaultLocation(locationWithName)
            }

        val lithoView = LithoView.create(
            c,
            createView(null)
                .build()
        )


        propDisposable = presenter
            .props()
            .subscribe({
                lithoView.setComponentAsync(
                    createView(
                        it.firstOrNull()
                    ).build()
                )
            }, ::onError)

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