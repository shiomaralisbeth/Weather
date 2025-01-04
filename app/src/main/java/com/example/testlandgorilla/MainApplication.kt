package com.example.testlandgorilla

import android.app.Application
import com.example.data.source.di.dataStoreModule
import com.example.data.source.di.networkModule
import com.example.data.source.di.repositoryModule
import com.example.data.source.di.roomModule
import com.example.domain.di.useCaseModule
import com.example.testlandgorilla.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    useCaseModule,
                    dataStoreModule,
                    repositoryModule,
                    networkModule,
                    viewModelModule,
                    roomModule
                )
            )
        }
    }
}