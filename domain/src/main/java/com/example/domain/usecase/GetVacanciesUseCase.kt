package com.example.domain.usecase

import com.example.domain.VacancyRepository
import com.example.domain.entity.Vacancy
import io.reactivex.Single

class GetVacanciesUseCase(
    private val vacancyRepository: VacancyRepository
) {
    fun execute(showAll: Boolean): Single<List<Vacancy>> {
        return vacancyRepository.getAllVacancy()
            .map { vacancies -> if (showAll) vacancies else vacancies.take(DEFAULT_LIMIT_VACANCIES) }
    }

    companion object {
        private const val DEFAULT_LIMIT_VACANCIES = 3
    }
}
