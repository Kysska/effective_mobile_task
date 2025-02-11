package com.example.effective_mobile_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.util.applySchedulers
import com.example.domain.VacancyRepository
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val vacancyRepository: VacancyRepository) : ViewModel() {

    private val _countFavoriteVacancies = MutableLiveData<Int>()
    val countVacancies: LiveData<Int>
        get() = _countFavoriteVacancies

    private val compositeDisposable = CompositeDisposable()

    fun loadCountFavoriteVacancies() {
        compositeDisposable.add(
            vacancyRepository.getFavoriteCountVacancy()
                .applySchedulers()
                .replay(1)
                .refCount()
                .subscribe({ count ->
                    _countFavoriteVacancies.value = count
                }, { error ->
                    _countFavoriteVacancies.value = 0
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
