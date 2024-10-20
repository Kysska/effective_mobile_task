package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("vacancies")
    val vacancyResponse: List<VacancyResponse>?,
    @SerializedName("offers")
    val offerResponse: List<OfferResponse>?
)
