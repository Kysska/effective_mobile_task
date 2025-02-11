package com.example.domain.usecase

import com.example.domain.VacancyRepository
import com.example.domain.entity.Vacancy
import io.reactivex.Observable
import io.reactivex.Single

class GetVacanciesUseCase(
    private val vacancyRepository: VacancyRepository
) {
    fun execute(showAll: Boolean): Observable<List<Vacancy>> {
        return vacancyRepository.getAllVacancy()
            .map { vacancies -> if (showAll) vacancies else vacancies.take(DEFAULT_LIMIT_VACANCIES) }
    }

    fun getAllVacanciesCount(): Single<Int> {
        return vacancyRepository.getAllVacancy()
            .map { it.size }
            .firstOrError()
    }

    companion object {
        private const val DEFAULT_LIMIT_VACANCIES = 3
    }
}
