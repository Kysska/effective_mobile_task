package com.example.data.repository.vacancy

import com.example.data.repository.vacancy.source.LocalVacancyDataSource
import com.example.data.repository.vacancy.source.RemoteVacancyDataSource
import com.example.domain.VacancyRepository
import com.example.domain.entity.Vacancy
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class VacancyRepositoryImpl(
    private val remoteVacancyDataSource: RemoteVacancyDataSource,
    private val localVacancyDataSource: LocalVacancyDataSource
) : VacancyRepository {
    override fun getAllVacancy(): Single<List<Vacancy>> {
        return remoteVacancyDataSource.getVacancies()
            .flatMap { vacancies ->
                val favoriteVacancies = vacancies.filter { it.isFavorite }

                if (favoriteVacancies.isNotEmpty()) {
                    Completable.merge(
                        favoriteVacancies.map { vacancy ->
                            localVacancyDataSource.insertVacancy(vacancy)
                        }
                    ).andThen(Single.just(vacancies))
                } else {
                    Single.just(vacancies)
                }
            }
    }

    override fun getAllFavoriteVacancy(): Observable<List<Vacancy>> {
        return localVacancyDataSource.getAllVacancies()
    }

    override fun insertVacancy(vacancy: Vacancy): Completable {
        return localVacancyDataSource.insertVacancy(vacancy)
    }

    override fun deleteVacancy(vacancy: Vacancy): Completable {
        return localVacancyDataSource.deleteVacancy(vacancy)
    }

    override fun isVacancyExists(id: String): Single<Boolean> {
        return localVacancyDataSource.isVacancyExists(id)
    }
}
