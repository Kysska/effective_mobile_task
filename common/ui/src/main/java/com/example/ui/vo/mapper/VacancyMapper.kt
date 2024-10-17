package com.example.ui.vo.mapper

import com.example.domain.entity.Vacancy
import com.example.ui.vo.VacancyView
import java.text.SimpleDateFormat
import java.util.Locale
import timber.log.Timber

object VacancyMapper {
    fun map(vacancy: Vacancy): VacancyView {
        return VacancyView(
            id = vacancy.id,
            title = vacancy.title,
            town = vacancy.town,
            company = vacancy.company,
            previewExperience = vacancy.previewExperience,
            isFavorite = vacancy.isFavorite,
            lookingNumber = vacancy.lookingNumber,
            formattedPublishedDate = formattedPublishedDate(vacancy.publishedDate)
        )
    }

    private fun formattedPublishedDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMMM", Locale("ru"))

        return try {
            val date = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) } ?: ""
        } catch (e: Exception) {
            Timber.tag("uncorrected date").e(e)
            return ""
        }
    }
}
