package com.example.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ui.view.ViewState
import com.example.core.util.applySchedulers
import com.example.domain.VacancyRepository
import com.example.ui.vo.VacancyView
import com.example.ui.vo.mapper.VacancyMapper
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class FavoriteViewModel(
    private val vacancyRepository: VacancyRepository
) : ViewModel() {

    private val _vacanciesState = MutableLiveData<ViewState<List<VacancyView>>>()
    val vacanciesState: LiveData<ViewState<List<VacancyView>>>
        get() = _vacanciesState

    private val _countVacancies = MutableLiveData<Int>()
    val countVacancies: LiveData<Int>
        get() = _countVacancies

    private val compositeDisposable = CompositeDisposable()

    fun loadVacancies() {
        _vacanciesState.value = ViewState.Loading
        compositeDisposable.add(
            vacancyRepository.getAllFavoriteVacancy()
                .applySchedulers()
                .subscribe({ vacancies ->
                    _vacanciesState.value = if (vacancies.isNotEmpty()) {
                        val vacancyViewModels = vacancies.map { VacancyMapper.map(it) }
                        ViewState.Success(vacancyViewModels)
                    } else {
                        ViewState.Success(emptyList())
                    }
                    getCountVacancies(vacancies.size)
                }, { error ->
                    _vacanciesState.value = ViewState.Error(error)
                })
        )
    }

    fun getCountVacancies(count: Int) {
        _countVacancies.value = count
    }

    fun onFavoriteCheckboxChanged(vacancyView: VacancyView, isChecked: Boolean) {
        val vacancy = VacancyMapper.reverseMap(vacancyView)
        if (isChecked) {
            compositeDisposable.add(
                vacancyRepository.insertVacancy(vacancy)
                    .applySchedulers()
                    .subscribe({
                        Timber.d("Vacancy saved to favorites")
                    }, { error ->
                        Timber.e(error, "Failed to save vacancy to favorites")
                    })
            )
        } else {
            compositeDisposable.add(
                vacancyRepository.deleteVacancy(vacancy)
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