package com.example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.databinding.EmptyDataBinding
import com.example.ui.databinding.VacancyItemBinding
import com.example.ui.databinding.VacancyLoadingItemBinding
import com.example.ui.utils.setFormattedText
import com.example.ui.view.ViewState
import com.example.ui.vo.VacancyView

class VacanciesAdapter(private val onFavoriteChanged: (VacancyView, Boolean) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var state: ViewState<List<VacancyView>> = ViewState.Loading

    companion object {
        private const val TYPE_LOADING = 0
        private const val TYPE_ERROR = 1
        private const val TYPE_VACANCY = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (state) {
            is ViewState.Loading -> TYPE_LOADING
            is ViewState.Error -> TYPE_ERROR
            is ViewState.Success -> TYPE_VACANCY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_LOADING -> LoadingViewHolder(VacancyLoadingItemBinding.inflate(inflater, parent, false))
            TYPE_ERROR -> ErrorViewHolder(EmptyDataBinding.inflate(inflater, parent, false))
            TYPE_VACANCY -> VacancyViewHolder(VacancyItemBinding.inflate(inflater, parent, false))
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LoadingViewHolder -> holder.bind()
            is ErrorViewHolder -> holder.bind()
            is VacancyViewHolder -> {
                val successState = state as ViewState.Success
                holder.bind(successState.data[position], onFavoriteChanged)
            }
        }
    }

    override fun getItemCount(): Int {
        return when (state) {
            is ViewState.Loading -> 10
            is ViewState.Success -> {
                val vacancies = (state as ViewState.Success).data
                if (vacancies.isEmpty()) 1 else vacancies.size
            }
            else -> 1
        }
    }

    fun updateState(newState: ViewState<List<VacancyView>>) {
        if (state is ViewState.Success && newState is ViewState.Success) {
            val diffResult = DiffUtil.calculateDiff(VacanciesDiffCallback((state as ViewState.Success).data, newState.data))
            state = newState
            diffResult.dispatchUpdatesTo(this)
        } else {
            state = newState
            notifyDataSetChanged()
        }
    }

    class LoadingViewHolder(private val binding: VacancyLoadingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

    class ErrorViewHolder(private val binding: EmptyDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }

    class VacancyViewHolder(private val binding: VacancyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vacancy: VacancyView, onFavoriteChanged: (VacancyView, Boolean) -> Unit) {
            binding.title.text = vacancy.title
            binding.town.text = vacancy.town
            binding.company.text = vacancy.company
            binding.publishedDate.setFormattedText(R.string.vacancy_card_published_date, vacancy.formattedPublishedDate)
            binding.nowWatchingText.setFormattedText(R.plurals.people, vacancy.lookingNumber)
            binding.experience.text = vacancy.previewExperience

            binding.favoriteCheckBox.setOnCheckedChangeListener(null)
            binding.favoriteCheckBox.isChecked = vacancy.isFavorite
            binding.favoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
                onFavoriteChanged(vacancy, isChecked)
            }
        }
    }
}

class VacanciesDiffCallback(
    private val oldList: List<VacancyView>,
    private val newList: List<VacancyView>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
