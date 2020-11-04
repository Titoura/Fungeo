package com.titou.database
//TODO: rename package to include fungeo

import android.content.Context
import androidx.room.*
import com.titou.database.dao.LocationWithNameDao
import com.titou.database.models.LocationWithName
import com.titou.database.typeConverters.LocationConverter

@androidx.room.Database(entities = [LocationWithName::class], version = DB_VERSION)
@TypeConverters(LocationConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun locationWithNAmeDao(): LocationWithNameDao

    companion object {
        @Volatile
        private var databseInstance: Database? = null

        fun getDatabasenIstance(mContext: Context): Database =
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
