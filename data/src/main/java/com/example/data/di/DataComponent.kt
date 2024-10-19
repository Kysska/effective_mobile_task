package com.example.data.di

import dagger.Component


@Component(modules = [RemoteDataModule::class, RepositoryModule::class])
class DataComponent {
}