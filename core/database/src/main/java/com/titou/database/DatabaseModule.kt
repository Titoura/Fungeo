package com.titou.database

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object DatabaseModule {
    val module = module {
        single { get<Database>().locationWithNAmeDao() }
        single {
            Room.databaseBuilder(androidApplication(), Database::class.java, "fungeo.db")
                .build()
        }


    }
}
