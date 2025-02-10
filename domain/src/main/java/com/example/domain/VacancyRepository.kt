package com.example.domain

import com.example.domain.entity.Vacancy
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface VacancyRepository {
    fun getAllVacancy(): Observable<List<Vacancy>>
    fun getAllFavoriteVacancy(): Observable<List<Vacancy>>
    fun insertVacancy(vacancy: Vacancy): Completable
    fun deleteVacancy(vacancy: Vacancy): Completable
    fun isVacancyExists(id: String): Single<Boolean>
}
