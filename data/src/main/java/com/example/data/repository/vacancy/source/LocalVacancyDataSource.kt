package com.example.data.repository.vacancy.source

import com.example.domain.entity.Vacancy
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface LocalVacancyDataSource {
    fun getAllVacancies(): Observable<List<Vacancy>>
    fun isVacancyExists(id: String): Single<Boolean>
    fun insertVacancy(vacancy: Vacancy): Completable
    fun deleteVacancy(vacancy: Vacancy): Completable
}
