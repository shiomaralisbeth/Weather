package com.example.data.source.di

import com.example.data.utils.Constants.Companion.DATA_BASE_NAME
import com.example.data.utils.DataBaseUtils
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val roomModule = module{
    single {
        DataBaseUtils.provideLocalDataBase(
            androidApplication()
        )
    }
    //Dao
    single {
        DataBaseUtils.provideSavedCitiesDao(get())
    }
}