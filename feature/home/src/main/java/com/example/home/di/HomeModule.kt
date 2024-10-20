package com.example.home.di

import com.example.domain.VacancyRepository
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetVacanciesUseCase
import com.example.home.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @FeatureScope
    fun provideHomeViewModel(getVacanciesUseCase: GetVacanciesUseCase, getOffersUseCase: GetOffersUseCase, vacancyRepository: VacancyRepository): HomeViewModel {
        return HomeViewModel(getVacanciesUseCase, getOffersUseCase, vacancyRepository)
    }
}
