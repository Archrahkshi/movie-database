package com.archrahkshi.moviedatabase

import android.app.Application
import timber.log.Timber

class MovieDatabaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
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
        var instance: MovieDatabaseApp? = null
            private set
    }
}
