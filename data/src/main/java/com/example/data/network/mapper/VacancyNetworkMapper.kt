package com.example.data.network.mapper

import com.example.data.network.dto.VacancyResponse
import com.example.domain.entity.Vacancy

internal object VacancyNetworkMapper : NetworkMapper<Vacancy, VacancyResponse> {
    override fun map(from: VacancyResponse): Vacancy {
        return Vacancy(
            id = from.id ?: "",
            title = from.title ?: "",
            town = from.address?.town ?: "",
            company = from.company ?: "",
            previewExperience = from.experience?.previewText ?: "",
            isFavorite = from.isFavorite ?: false,
            lookingNumber = from.lookingNumber ?: 0,
            publishedDate = from.publishedDate ?: ""
        )
    }
}
