package com.example.data.repository.vacancy

import com.example.data.repository.vacancy.source.RemoteVacancyDataSource
import com.example.domain.VacancyRepository
import com.example.domain.entity.Vacancy
import io.reactivex.Single

internal class VacancyRepositoryImpl(
    private val remoteVacancyDataSource: RemoteVacancyDataSource
) : VacancyRepository {
    override fun getAllVacancy(): Single<List<Vacancy>> {
        return remoteVacancyDataSource.getVacancies()
    }
}
