package com.example.data.repository.vacancy.source

import com.example.domain.entity.Vacancy
import io.reactivex.Single

interface RemoteVacancyDataSource {
    fun getVacancies(): Single<List<Vacancy>>
}
