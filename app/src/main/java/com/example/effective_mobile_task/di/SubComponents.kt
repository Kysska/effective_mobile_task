package com.example.effective_mobile_task.di

import com.example.favorite.di.FavoriteComponent
import com.example.favorite.di.FavoriteComponentProvider
import com.example.home.di.HomeComponent
import com.example.home.di.HomeComponentProvider

interface SubComponents : HomeComponentProvider, FavoriteComponentProvider {
    override fun provideHomeComponent(): HomeComponent {
        return DiProvider.appComponent().homeComponent.create()
    }

    override fun provideFavoriteComponent(): FavoriteComponent {
        return DiProvider.appComponent().favoriteComponent.create()
    }
}
