package com.example.domain.di

import com.example.domain.OfferRepository
import com.example.domain.VacancyRepository
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetVacanciesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGetVacanciesUseCase(repository: VacancyRepository): GetVacanciesUseCase {
        return GetVacanciesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetOffersUseCase(repository: OfferRepository): GetOffersUseCase {
        return GetOffersUseCase(repository)
    }
}