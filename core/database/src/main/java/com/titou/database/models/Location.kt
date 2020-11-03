package com.titou.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class Location(
    val name : String? = null,
    val longitude: Float = 0.0f,
    val latitude: Float = 0.0f
)
