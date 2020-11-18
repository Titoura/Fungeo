package com.titou.data_source.remote.weather_api.dtos

import com.google.gson.annotations.SerializedName

data class WeatherDescriptionResponseDto(
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?
)
