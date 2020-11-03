package com.titou.requestscontroller

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class RetrofitBuilder : KoinComponent {

    private val httpClient: OkHttpClient.Builder = OkHttpClient
        .Builder()
        .addInterceptor { chain ->
            val request: Request = chain
                .request()
                .newBuilder()
                .addHeader(
                    "Accept-language", Locale
                        .getDefault()
                        .language
                )
                .build()
            chain.proceed(request)
        }


    val retrofitInstance: Retrofit
        get() {

            //TODO: use buildconfig to store dev vs release environment
            // and centralize project values
            val baseUrl = "https://api.openweathermap.org/data/2.5/"

            return Retrofit
                .Builder()
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .serializeNulls()
                            .create()
                    )
                )
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build())
                .build()
        }


}


