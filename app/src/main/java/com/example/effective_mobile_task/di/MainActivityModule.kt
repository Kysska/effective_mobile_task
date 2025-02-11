package com.example.effective_mobile_task.di

import com.example.domain.VacancyRepository
import com.example.effective_mobile_task.MainActivityViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainActivityModule {
    @Provides
    @Singleton
    fun provideHomeViewModel(vacancyRepository: VacancyRepository): MainActivityViewModel {
        return MainActivityViewModel(vacancyRepository)
    }
}
