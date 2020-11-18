package com.titou.data_source.remote.weather_api.dtos

import com.google.gson.annotations.SerializedName

data class LocationResponseDto(
    @SerializedName("lat")
    val latitude: Float?,
    @SerializedName("long")
    val longitude: Float?
)
