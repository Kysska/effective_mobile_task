package com.example.domain

import com.example.domain.entity.Vacancy
import io.reactivex.Single

interface VacancyRepository {
    fun getAllVacancy(): Single<List<Vacancy>>
}
