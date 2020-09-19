package com.dino.something.application

import android.app.Application
import timber.log.Timber

class SomethingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var instance: SomethingApplication
            private set
    }
}