package com.example.effective_mobile_task.di

import android.app.Application
import android.content.Context
import com.example.data.di.RemoteDataModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.DomainModule
import com.example.home.di.HomeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class
    ]
)
@Singleton
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val homeComponent: HomeComponent.Factory
}