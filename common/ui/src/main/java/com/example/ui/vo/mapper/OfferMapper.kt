package com.example.ui.vo.mapper

import com.example.domain.entity.Offer
import com.example.ui.R
import com.example.ui.vo.OfferView

object OfferMapper {
    fun map(offer: Offer): OfferView {
        val iconResId = when (offer.id) {
            "near_vacancies" -> R.layout.icon_circle_vacancies_near
            "level_up_resume" -> R.layout.icon_circle_star
            else -> R.layout.icon_circle_checklist
        }
        return OfferView(
            id = offer.id,
            title = offer.title,
            link = offer.link,
            buttonText = offer.buttonText,
            iconRes = iconResId
        )
    }
}
