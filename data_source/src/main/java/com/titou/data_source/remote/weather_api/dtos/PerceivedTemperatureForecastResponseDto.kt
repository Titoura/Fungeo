package com.titou.data_source.remote.weather_api.dtos

import com.google.gson.annotations.SerializedName

data class PerceivedTemperatureForecastResponseDto(
    @SerializedName("day")
    val day: Float?,
    @SerializedName("night")
    val night: Float?
)
