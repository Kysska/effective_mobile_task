package com.example.data.repository.offer

import com.example.data.repository.offer.source.RemoteOfferDataSource
import com.example.domain.OfferRepository
import com.example.domain.entity.Offer
import io.reactivex.Single

internal class OfferRepositoryImpl(
    private val remoteOfferDataSource: RemoteOfferDataSource
) : OfferRepository {
    override fun getOffers(): Single<List<Offer>> {
        return remoteOfferDataSource.getOffers()
    }
}
