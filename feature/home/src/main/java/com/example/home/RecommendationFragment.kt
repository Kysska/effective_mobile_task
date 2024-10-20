package com.example.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.ViewState
import com.example.home.databinding.FragmentRecomendationBinding
import com.example.home.di.HomeComponentProvider
import com.example.ui.adapter.VacanciesAdapter
import com.example.ui.utils.setFormattedText
import com.example.ui.view.CustomViewInputEditText
import javax.inject.Inject

class RecommendationFragment : Fragment(R.layout.fragment_recomendation) {

    private var _binding: FragmentRecomendationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var homeViewModel: HomeViewModel

    private val vacanciesAdapter by lazy {
        VacanciesAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentComponent = (requireActivity() as HomeComponentProvider).provideHomeComponent()
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecomendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vacanciesRecyclerView.adapter = vacanciesAdapter

        setBackButton()
        observableViewModel()
    }

    private fun observableViewModel() {
        observableVacancies()
        observableCountVacancies()

        homeViewModel.loadVacancies(DEFAULT_SHOW_ALL_VACANCIES)
        homeViewModel.getCountVacancies()
    }

    private fun observableVacancies() {
        homeViewModel.vacanciesState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.progressBar.progressBar.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    vacanciesAdapter.submitList(state.data)
                    binding.progressBar.progressBar.visibility = View.GONE
                }
                is ViewState.Error -> {
                    binding.progressBar.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun observableCountVacancies() {
        homeViewModel.countVacancies.observe(viewLifecycleOwner) { count ->
            binding.countVacancy.setFormattedText(com.example.ui.R.string.recommendation_count_vacancy, count)
        }
    }

    private fun setBackButton() {
        binding.include.buttonBack.setImageResource(com.example.ui.R.drawable.baseline_arrow_back_24)
        binding.include.searchInput.setHint(com.example.ui.R.string.search_hint_text_recommendation_fragment)

        binding.include.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val DEFAULT_SHOW_ALL_VACANCIES = true
    }
}
