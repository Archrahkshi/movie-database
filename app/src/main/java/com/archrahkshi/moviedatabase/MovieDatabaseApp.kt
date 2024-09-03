package com.archrahkshi.moviedatabase

import android.app.Application
import android.content.Context
import timber.log.Timber

class MovieDatabaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        instance = this
        initDebugTools()
    }

    private fun initDebugTools() {
        if (BuildConfig.DEBUG) {
            initTimber()
        }
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var appContext: Context
        var instance: MovieDatabaseApp? = null
            private set
    }
}
