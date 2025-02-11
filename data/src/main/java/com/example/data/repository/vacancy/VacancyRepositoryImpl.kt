package com.example.data.repository.vacancy

import com.example.data.repository.vacancy.source.LocalVacancyDataSource
import com.example.data.repository.vacancy.source.RemoteVacancyDataSource
import com.example.domain.VacancyRepository
import com.example.domain.entity.Vacancy
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class VacancyRepositoryImpl(
    private val remoteVacancyDataSource: RemoteVacancyDataSource,
    private val localVacancyDataSource: LocalVacancyDataSource
) : VacancyRepository {
    private val vacancyCache: BehaviorSubject<List<Vacancy>> = BehaviorSubject.create()

    init {
        initializeVacancies()
            .subscribeOn(Schedulers.io())
            .onErrorComplete()
            .subscribe()
    }

    private fun initializeVacancies(): Completable {
        return remoteVacancyDataSource.getVacancies()
            .flatMapCompletable { updatedVacancies ->
                val favoriteVacancies = updatedVacancies.filter { it.isFavorite }
                val insertCompletable = if (favoriteVacancies.isNotEmpty()) {
                    Completable.merge(favoriteVacancies.map { localVacancyDataSource.insertVacancy(it) })
                } else {
                    Completable.complete()
                }
                insertCompletable.andThen(Completable.fromAction {
                    vacancyCache.onNext(updatedVacancies)
                })
            }
    }

    override fun getAllVacancy(): Observable<List<Vacancy>> {
        return vacancyCache
            .switchMap { vacancies ->
                getAllFavoriteVacancy()
                    .first(emptyList())
                    .map { favoriteVacancies ->
                        val favoriteIds = favoriteVacancies.map { it.id }.toSet()
                        vacancies.map { it.copy(isFavorite = it.id in favoriteIds) }
                    }.toObservable()
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

    override fun getFavoriteCountVacancy(): Observable<Int> {
        return localVacancyDataSource.getFavoriteCountVacancies()
    }
}
