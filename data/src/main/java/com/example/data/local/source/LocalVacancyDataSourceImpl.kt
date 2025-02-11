package com.example.data.local.source

import com.example.data.local.dao.VacancyDao
import com.example.data.local.mapper.VacancyDatabaseMapper
import com.example.data.repository.vacancy.source.LocalVacancyDataSource
import com.example.domain.entity.Vacancy
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber

class LocalVacancyDataSourceImpl(
    private val vacancyDao: VacancyDao,
    private val databaseMapper: VacancyDatabaseMapper
) : LocalVacancyDataSource {
    override fun getAllVacancies(): Observable<List<Vacancy>> {
        return vacancyDao.getAllFavoriteVacancies()
            .map { response -> databaseMapper.reverseMap(response) }
            .doOnError { throwable ->
                Timber.tag(TAG).e(throwable)
            }
    }

    override fun isVacancyExists(id: String): Single<Boolean> {
        return vacancyDao.isVacancyExists(id)
            .doOnError { throwable ->
                Timber.tag(TAG).e(throwable)
            }
    }

    override fun insertVacancy(vacancy: Vacancy): Completable {
        val vacancyDb = databaseMapper.map(vacancy)
        return vacancyDao.insertVacancy(vacancyDb)
            .doOnComplete {
                Timber.tag(TAG).d("Insert completed successfully")
            }
            .doOnError { throwable ->
                Timber.tag(TAG).e(throwable)
            }
    }

    override fun deleteVacancy(vacancy: Vacancy): Completable {
        val vacancyDb = databaseMapper.map(vacancy)
        return vacancyDao.deleteVacancy(vacancyDb)
            .doOnComplete {
                Timber.tag(TAG).d("Delete completed successfully")
            }
            .doOnError { throwable ->
                Timber.tag(TAG).e(throwable)
            }
    }

    override fun getFavoriteCountVacancies(): Observable<Int> {
        return vacancyDao.getCountFavoriteVacancies()
            .doOnError { throwable ->
                Timber.tag(TAG).e(throwable)
            }
    }

    companion object {
        private const val TAG = "LocalVacancy"
    }
}
