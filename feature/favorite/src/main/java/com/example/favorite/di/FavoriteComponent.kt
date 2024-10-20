package com.example.favorite.di

import com.example.favorite.FavoriteFragment
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureFavScope

@FeatureFavScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoriteComponent {
    fun inject(favoriteFragment: FavoriteFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteComponent
    }
}