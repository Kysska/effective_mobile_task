package com.example.data.di

import android.content.Context
import com.example.data.local.dao.VacancyDao
import com.example.data.local.database.VacancyDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): VacancyDatabase {
        return VacancyDatabase.invoke(context)
    }

    @Provides
    @Singleton
    fun provideMovieCardDao(database: VacancyDatabase): VacancyDao {
        return database.vacancyDao()
    }
}
