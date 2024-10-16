package com.example.data.repository.offer.source

import com.example.domain.entity.Offer
import io.reactivex.Single

interface RemoteOfferDataSource {
    fun getOffers(): Single<List<Offer>>
}
