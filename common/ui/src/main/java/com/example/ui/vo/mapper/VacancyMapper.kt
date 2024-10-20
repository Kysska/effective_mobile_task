package com.example.ui.vo.mapper

import com.example.domain.entity.Vacancy
import com.example.ui.vo.VacancyView
import java.text.SimpleDateFormat
import java.util.Locale
import timber.log.Timber

object VacancyMapper : ViewMapper<Vacancy, VacancyView> {
    override fun map(vacancy: Vacancy): VacancyView {
        return VacancyView(
            id = vacancy.id,
            title = vacancy.title,
            town = vacancy.town,
            company = vacancy.company,
            previewExperience = vacancy.previewExperience,
            isFavorite = vacancy.isFavorite,
            lookingNumber = vacancy.lookingNumber.toString(),
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

    override fun reverseMap(to: VacancyView): Vacancy {
        return Vacancy(
            id = to.id,
            title = to.title,
            town = to.town,
            company = to.company,
            previewExperience = to.previewExperience,
            isFavorite = to.isFavorite,
            lookingNumber = to.lookingNumber.toInt(),
            publishedDate = to.formattedPublishedDate
        )
    }
}
