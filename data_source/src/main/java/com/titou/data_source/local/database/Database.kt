package com.titou.data_source.local.database
//TODO: rename package to include fungeo

import android.content.Context
import androidx.room.*
import com.titou.data_source.local.database.dao.LocationWithNameDao
import com.titou.data_source.local.database.dtos.LocationWithNameRoomModel
import com.titou.data_source.local.database.typeConverters.LocationConverter

@androidx.room.Database(entities = [LocationWithNameRoomModel::class], version = DB_VERSION)
@TypeConverters(LocationConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun locationWithNameRoomModelDao(): LocationWithNameDao

    companion object {
        @Volatile
        private var databseInstance: Database? = null

        fun getDatabaseInstance(mContext: Context): Database =
            databseInstance ?: synchronized(this) {
                databseInstance ?: buildDatabaseInstance(mContext).also {
                    databseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, Database::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

    }
}


const val DB_VERSION = 1

const val DB_NAME = "Fungeo.db"
