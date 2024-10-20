package com.example.data.network.source

import com.example.data.network.ApiInterface
import com.example.data.network.mapper.OfferNetworkMapper
import com.example.data.repository.offer.source.RemoteOfferDataSource
import com.example.domain.entity.Offer
import io.reactivex.Single
import timber.log.Timber

class RemoteOfferDataSourceImpl(
    private val apiInterface: ApiInterface,
    private val networkMapper: OfferNetworkMapper
) : RemoteOfferDataSource {
    override fun getOffers(): Single<List<Offer>> {
        return apiInterface.getAllResponse()
            .map { response ->
                networkMapper.map(response.offerResponse ?: emptyList())
            }
            .doOnError { throwable ->
                Timber.tag("offer data source").e(throwable)
            }
    }
}
