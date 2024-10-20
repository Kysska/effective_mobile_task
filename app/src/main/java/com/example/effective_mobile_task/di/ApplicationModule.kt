package com.example.effective_mobile_task.di

import com.example.data.di.RemoteDataModule
import com.example.data.di.RepositoryModule
import com.example.domain.di.DomainModule
import com.example.home.di.HomeComponent
import dagger.Module

@Module(
    subcomponents = [HomeComponent::class],
    includes = [RemoteDataModule::class,
        RepositoryModule::class,
        DomainModule::class]
)
class ApplicationModule