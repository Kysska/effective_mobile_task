package com.example.domain.utils

object DatabaseConstants {
    const val DATABASE_NAME = "vacancy_database"
    const val VACANCY_TABLE = "vacancy"

    object VacancyColumns {
        const val ID = "id"
        const val TITLE = "title"
        const val TOWN = "town"
        const val COMPANY = "company"
        const val PREVIEW_EXPERIENCE = "preview_experience"
        const val IS_FAVORITE = "is_favorite"
        const val LOOKING_NUMBER = "looking_number"
        const val PUBLISHED_DATE = "published_date"
    }
}
