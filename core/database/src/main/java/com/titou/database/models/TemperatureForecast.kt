package com.titou.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class TemperatureForecast(
    val day: Float? = 0f,
    val min: Float? = 0f,
    val max: Float? = 0f,
    val night: Float? = 0f
)
