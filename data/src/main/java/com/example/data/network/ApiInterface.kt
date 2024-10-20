package com.example.data.network

import com.example.data.network.dto.MainResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("u/0/uc")
    fun getAllResponse(
        @Query("id") id: String = ID_FILE,
        @Query("export") export: String = EXPORT_FILE
    ): Single<MainResponse>

    companion object {
        private const val ID_FILE = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"
        private const val EXPORT_FILE = "download"
    }
}
