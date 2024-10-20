package com.example.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.ViewState
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.FavoriteComponentProvider
import com.example.ui.utils.FavoriteEvents
import com.example.ui.adapter.VacanciesAdapter
import com.example.ui.utils.setFormattedText
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
            when (state) {
                is ViewState.Loading -> {
                    binding.progressBar.progressBar.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.progressBar.progressBar.visibility = View.GONE
                    vacanciesAdapter.submitList(state.data)
                }
                is ViewState.Error -> {
                    binding.progressBar.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun observableCountVacancies() {
        favoriteViewModel.countVacancies.observe(viewLifecycleOwner) { count ->
            binding.countVacancy.setFormattedText(com.example.ui.R.string.favorite_vacancy_count, count)
            FavoriteEvents.postFavoriteCount(count)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

    }
}