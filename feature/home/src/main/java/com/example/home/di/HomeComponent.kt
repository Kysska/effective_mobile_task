package com.example.home.di

import com.example.home.HomeFragment
import com.example.home.RecommendationFragment
import dagger.Subcomponent
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope

@FeatureScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(recommendationFragment: RecommendationFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }
}
