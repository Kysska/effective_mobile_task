package com.example.favorite.di

import com.example.domain.VacancyRepository
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetVacanciesUseCase
import com.example.favorite.FavoriteViewModel
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @Provides
    @FeatureFavScope
    fun provideFavoriteViewModel(repository: VacancyRepository): FavoriteViewModel {
        return FavoriteViewModel(repository)
    }
}