package com.example.domain.usecase

import com.example.domain.OfferRepository
import com.example.domain.entity.Offer
import io.reactivex.Single

class GetOffersUseCase(
    private val offerRepository: OfferRepository
) {
    fun execute(): Single<List<Offer>> {
        return offerRepository.getOffers()
    }
}
