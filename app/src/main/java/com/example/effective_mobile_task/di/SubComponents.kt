package com.example.effective_mobile_task.di

import com.example.home.di.HomeComponent
import com.example.home.di.HomeComponentProvider
import timber.log.Timber

interface SubComponents: HomeComponentProvider {
    override fun provideHomeComponent(): HomeComponent {
        return DiProvider.appComponent().homeComponent.create()
    }
}