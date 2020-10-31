package com.titou.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "cities", primaryKeys = ["name", "country"])
data class City(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "country")
    val country: String
)
