package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.ViewState
import com.example.core.util.applySchedulers
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetVacanciesUseCase
import com.example.ui.vo.OfferView
import com.example.ui.vo.VacancyView
import com.example.ui.vo.mapper.OfferMapper
import com.example.ui.vo.mapper.VacancyMapper
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getOffersUseCase: GetOffersUseCase
) : ViewModel() {

    private val _vacanciesState = MutableLiveData<ViewState<List<VacancyView>>>()
    val vacanciesState: LiveData<ViewState<List<VacancyView>>>
        get() = _vacanciesState

    private val _countVacancies = MutableLiveData<Int>()
    val countVacancies: LiveData<Int>
        get() = _countVacancies

    private val _offersState = MutableLiveData<ViewState<List<OfferView>>>()
    val offerState: LiveData<ViewState<List<OfferView>>>
        get() = _offersState

    private val compositeDisposable = CompositeDisposable()

    fun loadOffers() {
        _offersState.value = ViewState.Loading
        compositeDisposable.add(
            getOffersUseCase.execute()
                .applySchedulers()
                .subscribe({ offers ->
                    _offersState.value = if (offers.isNotEmpty()) {
                        val offerViewModels = offers.map { OfferMapper.map(it) }
                        ViewState.Success(offerViewModels)
                    } else {
                        ViewState.Success(emptyList())
                    }
                }, { error ->
                    _offersState.value = ViewState.Error(error)
                })
        )
    }

    fun loadVacancies(showAll: Boolean) {
        _vacanciesState.value = ViewState.Loading
        compositeDisposable.add(
            getVacanciesUseCase.execute(showAll)
                .applySchedulers()
                .subscribe({ vacancies ->
                    _vacanciesState.value = if (vacancies.isNotEmpty()) {
                        val vacancyViewModels = vacancies.map { VacancyMapper.map(it) }
                        ViewState.Success(vacancyViewModels)
                    } else {
                        ViewState.Success(emptyList())
                    }
                }, { error ->
                    _vacanciesState.value = ViewState.Error(error)
                })
        )
    }

    fun getCountVacancies() {
        compositeDisposable.add(
            getVacanciesUseCase.getAllVacanciesCount()
                .applySchedulers()
                .subscribe({ count ->
                    _countVacancies.value = count
                }, { error ->
                    _countVacancies.value = 0
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
