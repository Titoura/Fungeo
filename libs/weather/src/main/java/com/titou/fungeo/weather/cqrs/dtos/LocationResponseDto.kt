package com.titou.fungeo.weather.cqrs.dtos

import com.google.gson.annotations.SerializedName

data class LocationResponseDto(
    @SerializedName("lat")
    val latitude: Float?,
    @SerializedName("long")
    val longitude: Float?
)
