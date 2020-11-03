package com.titou.fungeo.weather.cqrs.dtos

import com.google.gson.annotations.SerializedName

data class WeatherDescriptionResponseDto(
    @SerializedName("main")
    val main: String?,
    @SerializedName("description")
    val description: String?
)
