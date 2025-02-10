package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.util.applySchedulers
import com.example.domain.VacancyRepository
import com.example.domain.usecase.GetOffersUseCase
import com.example.domain.usecase.GetVacanciesUseCase
import com.example.ui.view.ViewState
import com.example.ui.vo.OfferView
import com.example.ui.vo.VacancyView
import com.example.ui.vo.mapper.OfferMapper
import com.example.ui.vo.mapper.VacancyMapper
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class HomeViewModel(
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val getOffersUseCase: GetOffersUseCase,
    private val repository: VacancyRepository
) : ViewModel() {

    private val _vacanciesState = MutableLiveData<ViewState<List<VacancyView>>>()
    val vacanciesState: LiveData<ViewState<List<VacancyView>>>
        get() = _vacanciesState

    private val _countVacancies = MutableLiveData<Int>()
    val countVacancies: LiveData<Int>
        get() = _countVacancies

    private val _countFavoriteVacancies = MutableLiveData<Int>()
    val countFavoriteVacancies: LiveData<Int>
        get() = _countFavoriteVacancies

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

    fun onFavoriteCheckboxChanged(vacancyView: VacancyView, isChecked: Boolean) {
        val vacancy = VacancyMapper.reverseMap(vacancyView)
        if (isChecked) {
            vacancy.isFavorite = isChecked
            compositeDisposable.add(
                repository.insertVacancy(vacancy)
                    .applySchedulers()
                    .subscribe({
                        Timber.d("Vacancy saved to favorites")
                    }, { error ->
                        Timber.e(error, "Failed to save vacancy to favorites")
                    })
            )
        } else {
            vacancy.isFavorite = isChecked
            compositeDisposable.add(
                repository.deleteVacancy(vacancy)
                    .applySchedulers()
                    .subscribe({
                        Timber.d("Vacancy removed from favorites")
                    }, { error ->
                        Timber.e(error, "Failed to remove vacancy from favorites")
                    })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
