package com.example.ui.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object FavoriteEvents {
    private val _favoriteCount = MutableLiveData<Int>()
    val favoriteCount: LiveData<Int> get() = _favoriteCount

    fun postFavoriteCount(count: Int) {
        _favoriteCount.value = count
    }
}
