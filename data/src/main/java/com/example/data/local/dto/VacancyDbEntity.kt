package com.example.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.utils.DatabaseConstants

@Entity(tableName = DatabaseConstants.VACANCY_TABLE)
data class VacancyDbEntity(
    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.ID)
    val id: String,
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.TITLE)
    val title: String,
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.TOWN)
    val town: String,
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.COMPANY)
    val company: String,
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.PREVIEW_EXPERIENCE)
    val previewExperience: String,
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.IS_FAVORITE)
    val isFavorite: Boolean,
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.LOOKING_NUMBER)
    val lookingNumber: Int,
    @ColumnInfo(name = DatabaseConstants.VacancyColumns.PUBLISHED_DATE)
    val publishedDate: String
)
