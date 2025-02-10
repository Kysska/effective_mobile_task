package com.example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.R
import com.example.ui.databinding.VacancyItemBinding
import com.example.ui.utils.setFormattedText
import com.example.ui.vo.VacancyView

class VacanciesAdapter(private val onFavoriteChanged: (VacancyView, Boolean) -> Unit) : RecyclerView.Adapter<VacanciesAdapter.VacancyViewHolder>() {

    private var vacancies: List<VacancyView> = emptyList()

    inner class VacancyViewHolder(val binding: VacancyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vacancyView: VacancyView) {
            binding.title.text = vacancyView.title
            binding.town.text = vacancyView.town
            binding.company.text = vacancyView.company
            binding.publishedDate.setFormattedText(R.string.vacancy_card_published_date, vacancyView.formattedPublishedDate)
            binding.nowWatchingText.setFormattedText(R.plurals.people, vacancyView.lookingNumber)
            binding.experience.text = vacancyView.previewExperience
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        val binding = VacancyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        if (position >= vacancies.size) return

        holder.bind(vacancies[position])

        holder.binding.favoriteCheckBox.setOnCheckedChangeListener(null)
        holder.binding.favoriteCheckBox.isChecked = vacancies[position].isFavorite
        holder.binding.favoriteCheckBox.setOnCheckedChangeListener { _, isChecked ->
            onFavoriteChanged(vacancies[position], isChecked)
        }
    }

    override fun getItemCount(): Int = vacancies.size

    fun submitList(newVacancies: List<VacancyView>) {
        val diffResult = DiffUtil.calculateDiff(VacanciesDiffCallback(vacancies, newVacancies))
        vacancies = newVacancies
        diffResult.dispatchUpdatesTo(this)
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
