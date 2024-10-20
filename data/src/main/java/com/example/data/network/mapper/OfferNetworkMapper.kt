package com.example.data.network.mapper

import com.example.data.network.dto.OfferResponse
import com.example.domain.entity.Offer

object OfferNetworkMapper : NetworkMapper<Offer, OfferResponse> {
    override fun map(from: OfferResponse): Offer {
        return Offer(
            id = from.id ?: "",
            title = from.title ?: "",
            link = from.link ?: "",
            buttonText = from.button?.text ?: ""
        )
    }
}
