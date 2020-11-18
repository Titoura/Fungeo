package com.titou.data_source.remote.weather_api.dtos

import com.google.gson.annotations.SerializedName

data class TemperatureForecastResponseDto(
    @SerializedName("day")
    val day: Float?,
    @SerializedName("min")
    val min: Float?,
    @SerializedName("max")
    val max: Float?,
    @SerializedName("night")
    val night: Float?
)
