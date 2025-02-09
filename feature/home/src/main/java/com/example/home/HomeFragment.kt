package com.example.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.home.databinding.FragmentHomeBinding
import com.example.home.di.HomeComponentProvider
import com.example.home.navigation.NavigationInterface
import com.example.ui.adapter.OffersAdapter
import com.example.ui.adapter.VacanciesAdapter
import com.example.ui.utils.FavoriteEvents
import com.example.ui.utils.setFormattedText
import com.example.ui.view.ViewState
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var homeViewModel: HomeViewModel

    private val offersAdapter by lazy {
        OffersAdapter()
    }

    private val vacanciesAdapter by lazy {
        VacanciesAdapter { vacancy, isCheched -> homeViewModel.onFavoriteCheckboxChanged(vacancy, isCheched) }
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.offerRecyclerView.adapter = offersAdapter
        binding.vacanciesRecyclerView.adapter = vacanciesAdapter

        observableViewModel()

        binding.floatingButton.setOnClickListener {
            (requireActivity() as NavigationInterface).navigateToRecommendationFragment()
        }
    }

    private fun observableViewModel() {
        observableOffers()
        observableVacancies()
        observableCountVacancies()

        homeViewModel.loadOffers()
        homeViewModel.loadVacancies(DEFAULT_SHOW_ALL_VACANCIES)
        homeViewModel.getCountVacancies()
    }

    private fun observableOffers() {
        homeViewModel.offerState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.progressBar.progressBar.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.progressBar.progressBar.visibility = View.GONE
                    offersAdapter.submitList(state.data)
                }
                is ViewState.Error -> {
                    binding.progressBar.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun observableVacancies() {
        homeViewModel.vacanciesState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.progressBar.progressBar.visibility = View.VISIBLE
                }
                is ViewState.Success -> {
                    binding.progressBar.progressBar.visibility = View.GONE
                    if (state.data.isNotEmpty()) {
                        vacanciesAdapter.submitList(state.data)
                    }
                }
                is ViewState.Error -> {
                    binding.progressBar.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun observableCountVacancies() {
        homeViewModel.countVacancies.observe(viewLifecycleOwner) { count ->
            binding.floatingButton.setFormattedText(com.example.ui.R.string.home_title_button, count)
        }
    }

    private fun observableCountFavoriteVacancies() {
        homeViewModel.countVacancies.observe(viewLifecycleOwner) { count ->
            FavoriteEvents.postFavoriteCount(count)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val DEFAULT_SHOW_ALL_VACANCIES = false
    }
}
