package com.example.data.network

import com.example.data.network.dto.OfferResponse
import com.example.data.network.dto.VacancyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("u/0/uc")
    fun getAllOffers(
        @Query("id") id: String = ID_FILE,
        @Query("export") export: String = EXPORT_FILE
    ): Single<List<OfferResponse>>

    @GET("u/0/uc/vacancies")
    fun getAllVacancies(
        @Query("id") id: String = ID_FILE,
        @Query("export") export: String = EXPORT_FILE
    ): Single<List<VacancyResponse>>

    companion object {
        private const val ID_FILE = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"
        private const val EXPORT_FILE = "download"
    }
}
