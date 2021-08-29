package com.dizcoding.miniproject

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.dizcoding.miniproject.data.di.jsonPlaceholderNetworkModule
import com.dizcoding.miniproject.data.di.jsonPlaceholderRepositoryModule
import com.dizcoding.miniproject.di.useCaseModule
import com.dizcoding.miniproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    jsonPlaceholderNetworkModule,
                    jsonPlaceholderRepositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}