package com.example.data.network.source

import com.example.data.network.ApiInterface
import com.example.data.network.mapper.VacancyNetworkMapper
import com.example.data.repository.vacancy.source.RemoteVacancyDataSource
import com.example.domain.entity.Vacancy
import io.reactivex.Single
import timber.log.Timber

class RemoteVacancyDataSourceImpl(
    private val apiInterface: ApiInterface,
    private val networkMapper: VacancyNetworkMapper
) : RemoteVacancyDataSource {
    override fun getVacancies(): Single<List<Vacancy>> {
        return apiInterface.getAllVacancies()
            .map { response ->
                networkMapper.map(response)
            }
            .doOnError { throwable ->
                Timber.tag("offer data source").e(throwable)
            }
    }
}
