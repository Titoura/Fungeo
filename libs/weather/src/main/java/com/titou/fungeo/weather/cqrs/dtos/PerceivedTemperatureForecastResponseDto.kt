package com.titou.fungeo.weather.cqrs.dtos

import com.google.gson.annotations.SerializedName

data class PerceivedTemperatureForecastResponseDto(
    @SerializedName("day")
    val day: Float?,
    @SerializedName("night")
    val night: Float?
)
