package com.example.data.di

import com.example.data.repository.offer.OfferRepositoryImpl
import com.example.data.repository.offer.source.RemoteOfferDataSource
import com.example.data.repository.vacancy.VacancyRepositoryImpl
import com.example.data.repository.vacancy.source.RemoteVacancyDataSource
import com.example.domain.OfferRepository
import com.example.domain.VacancyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideOfferRepository(
        remoteOfferDataSource: RemoteOfferDataSource
    ): OfferRepository {
        return OfferRepositoryImpl(
            remoteOfferDataSource
        )
    }

    @Provides
    @Singleton
    fun provideVacancyRepository(
        remoteVacancyDataSource: RemoteVacancyDataSource
    ): VacancyRepository {
        return VacancyRepositoryImpl(
            remoteVacancyDataSource
        )
    }
}
