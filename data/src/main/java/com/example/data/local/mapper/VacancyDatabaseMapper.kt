package com.example.data.local.mapper

import com.example.data.local.dto.VacancyDbEntity
import com.example.domain.entity.Vacancy

object VacancyDatabaseMapper : DatabaseMapper<Vacancy, VacancyDbEntity> {
    override fun map(from: Vacancy): VacancyDbEntity {
        return VacancyDbEntity(
            id = from.id,
            title = from.title,
            town = from.town,
            company = from.company,
            previewExperience = from.previewExperience,
            isFavorite = from.isFavorite,
            lookingNumber = from.lookingNumber,
            publishedDate = from.publishedDate
        )
    }

    override fun reverseMap(to: VacancyDbEntity): Vacancy {
        return Vacancy(
            id = to.id,
            title = to.title,
            town = to.town,
            company = to.company,
            previewExperience = to.previewExperience,
            isFavorite = to.isFavorite,
            lookingNumber = to.lookingNumber,
            publishedDate = to.publishedDate
        )
    }
}
