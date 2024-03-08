package com.kueski.marktest

import android.app.Application
import com.kueski.marktest.di.apiClient
import com.kueski.marktest.di.database
import com.kueski.marktest.di.repositories
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class KueskiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startDI()
    }


    private fun startDI() {
        startKoin {
            androidContext(this@KueskiApp)
            modules(listOf(repositories, apiClient, database))
        }
    }
}