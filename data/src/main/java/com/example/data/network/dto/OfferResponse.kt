package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class OfferResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("button")
    val button: ButtonInfoResponse?
)

data class ButtonInfoResponse(
    @SerializedName("text")
    val text: String?
)
