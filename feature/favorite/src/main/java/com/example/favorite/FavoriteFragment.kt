package com.example.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.ui.view.ViewState
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.FavoriteComponentProvider
import com.example.ui.adapter.VacanciesAdapter
import com.example.ui.utils.setFormattedText
import timber.log.Timber
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var favoriteViewModel: FavoriteViewModel

    private val vacanciesAdapter by lazy {
        VacanciesAdapter  { vacancy, isCheched -> favoriteViewModel.onFavoriteCheckboxChanged(vacancy, isCheched) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentComponent = (requireActivity() as FavoriteComponentProvider).provideFavoriteComponent()
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteRecyclerView.adapter = vacanciesAdapter

        observableViewModel()
    }

    private fun observableViewModel() {
        observableVacancies()
        observableCountVacancies()

        favoriteViewModel.loadVacancies()
    }

    private fun observableVacancies() {
        favoriteViewModel.vacanciesState.observe(viewLifecycleOwner) { state ->
            binding.progressBar.progressBar.isVisible = state is ViewState.Loading
            binding.favoriteRecyclerView.isVisible = state is ViewState.Success && state.data.isNotEmpty()

            vacanciesAdapter.updateState(state)
        }
    }

    private fun observableCountVacancies() {
        favoriteViewModel.countVacancies.observe(viewLifecycleOwner) { count ->
            binding.countVacancy.setFormattedText(com.example.ui.R.plurals.vacancies, count)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}