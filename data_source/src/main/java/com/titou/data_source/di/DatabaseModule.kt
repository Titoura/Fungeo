package com.titou.data_source.di

import androidx.room.Room
import com.titou.data_source.local.database.Database
import com.titou.data_source.local.database.DatabaseLocationSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.bind
import org.koin.dsl.module

object DatabaseModule {
    val module = module {

        single { get<Database>().locationWithNameRoomModelDao() }

        single {
            Room.databaseBuilder(androidApplication(), Database::class.java, "fungeo.db")
                .build()
        }

        single { DatabaseLocationSource(get()) } bind (com.titou.use_cases.DatabaseLocationSource::class)
    }
}