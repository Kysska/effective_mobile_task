package com.example.effective_mobile_task

import android.app.Application
import com.example.effective_mobile_task.di.AppComponent
import com.example.effective_mobile_task.di.DiProvider
import com.example.effective_mobile_task.di.SubComponents
import timber.log.Timber

class JobFinderApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDebugTools()
        DiProvider.buildDi(this)
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
        var instance:JobFinderApp? = null
            private set
    }
}