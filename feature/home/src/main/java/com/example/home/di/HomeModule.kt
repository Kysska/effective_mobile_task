package com.example.home.di

import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetVacanciesUseCase
import com.example.home.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @FeatureScope
    fun provideHomeViewModel(getVacanciesUseCase: GetVacanciesUseCase, getOffersUseCase: GetOffersUseCase): HomeViewModel {
        return HomeViewModel(getVacanciesUseCase, getOffersUseCase)
    }
}
