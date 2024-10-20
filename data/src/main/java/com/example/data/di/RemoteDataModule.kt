package com.example.data.di

import com.example.data.network.ApiClient
import com.example.data.network.mapper.OfferNetworkMapper
import com.example.data.network.mapper.VacancyNetworkMapper
import com.example.data.network.source.RemoteOfferDataSourceImpl
import com.example.data.network.source.RemoteVacancyDataSourceImpl
import com.example.data.repository.offer.source.RemoteOfferDataSource
import com.example.data.repository.vacancy.source.RemoteVacancyDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {
    @Provides
    @Singleton
    fun provideOfferRemoteDataSource(): RemoteOfferDataSource {
        return RemoteOfferDataSourceImpl(
            ApiClient.apiClient,
            OfferNetworkMapper
        )
    }

    @Provides
    @Singleton
    fun provideVacancyRemoteDataSource(): RemoteVacancyDataSource {
        return RemoteVacancyDataSourceImpl(
            ApiClient.apiClient,
            VacancyNetworkMapper
        )
    }
}
