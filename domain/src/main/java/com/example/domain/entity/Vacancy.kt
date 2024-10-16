package com.example.domain.entity


data class Vacancy(
    val id: String = "",
    val title: String = "",
    val town: String = "",
    val company: String = "",
    val previewExperience: String = "",
    val isFavorite: Boolean = false,
    val lookingNumber: Int = 0,
    val publishedDate: String = ""
)
