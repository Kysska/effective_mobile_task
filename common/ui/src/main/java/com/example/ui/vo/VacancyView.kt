package com.example.ui.vo

data class VacancyView(
    val id: String,
    val title: String,
    val town: String,
    val company: String,
    val previewExperience: String,
    val isFavorite: Boolean,
    val lookingNumber: Int,
    val formattedPublishedDate: String
)
