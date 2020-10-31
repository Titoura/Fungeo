package com.titou.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.titou.database.models.WeatherWithCity
import io.reactivex.rxjava3.disposables.Disposable
import org.koin.androidx.scope.lifecycleScope


class HomeFragment(parent: AppCompatActivity) : Fragment() {

    private val presenter: HomePresenter by lifecycleScope.inject()

    private lateinit var propDisposable: Disposable

    init {
        lifecycleScope.linkTo(parent.lifecycleScope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val c = ComponentContext(context)

        val createView =
            {
                HomeView
                    .create(c)
            }


        val lithoView = LithoView.create(
            c,
            createView()
                .build()
        )


        propDisposable = presenter
            .props()
            .subscribe({
                lithoView.setComponentAsync(
                    createView(
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

data class Props(
    val weatherWithCity: WeatherWithCity
)
