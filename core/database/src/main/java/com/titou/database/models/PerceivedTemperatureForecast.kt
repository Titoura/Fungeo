package com.titou.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


data class PerceivedTemperatureForecast(

    val day: Float? = 0f,
    val night: Float? = 0f
)
