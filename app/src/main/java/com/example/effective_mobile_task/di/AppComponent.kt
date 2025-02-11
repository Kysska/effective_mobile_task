package com.example.effective_mobile_task.di

import android.content.Context
import com.example.favorite.di.FavoriteComponent
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
    val favoriteComponent: FavoriteComponent.Factory
    val mainActivityComponent: MainActivityComponent.Factory
}
