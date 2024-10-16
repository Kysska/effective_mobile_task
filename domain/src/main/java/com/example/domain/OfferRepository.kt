package com.example.domain

import com.example.domain.entity.Offer
import io.reactivex.Single

interface OfferRepository {
    fun getOffers(): Single<List<Offer>>
}