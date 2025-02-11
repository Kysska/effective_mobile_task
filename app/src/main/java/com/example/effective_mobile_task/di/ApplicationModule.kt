package com.example.effective_mobile_task.di

import com.example.data.di.DatabaseModule
import com.example.data.di.LocalDataModule
import com.example.data.di.RemoteDataModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.DomainModule
import com.example.favorite.di.FavoriteComponent
import com.example.home.di.HomeComponent
import dagger.Module

@Module(
    subcomponents = [HomeComponent::class, FavoriteComponent::class, MainActivityComponent::class],
    includes = [RemoteDataModule::class,
        LocalDataModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        DomainModule::class,
        MainActivityModule::class]
)
class ApplicationModule
