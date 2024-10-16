package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class VacancyResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("address")
    val address: AddressResponse?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("experience")
    val experience: ExperienceResponse?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean?,
    @SerializedName("salary")
    val salary: SalaryResponse?,
    @SerializedName("schedules")
    val schedules: List<String>?,
    @SerializedName("appliedNumber")
    val appliedNumber: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("responsibilities")
    val responsibilities: String,
    @SerializedName("questions")
    val questions: List<String>,
    @SerializedName("lookingNumber")
    val lookingNumber: Int?
)

data class AddressResponse(
    @SerializedName("town") val town: String?,
    @SerializedName("street") val street: String?,
    @SerializedName("home") val house: String?
)

data class ExperienceResponse(
    @SerializedName("previewText") val previewText: String?,
    @SerializedName("text") val text: String?
)

data class SalaryResponse(@SerializedName("full") val full: String?)
