package com.example.data.di

import com.example.data.local.dao.VacancyDao
import com.example.data.local.mapper.VacancyDatabaseMapper
import com.example.data.local.source.LocalVacancyDataSourceImpl
import com.example.data.repository.vacancy.source.LocalVacancyDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideVacancyLocalDataSource(vacancyDao: VacancyDao): LocalVacancyDataSource {
        return LocalVacancyDataSourceImpl(
            vacancyDao,
            VacancyDatabaseMapper
        )
    }
}
