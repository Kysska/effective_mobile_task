package com.example.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.OfferItemBinding
import com.example.ui.utils.openUrl
import com.example.ui.utils.setTextOrHide
import com.example.ui.vo.OfferView

class OffersAdapter : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>() {

    private var offers: List<OfferView> = emptyList()

    inner class OfferViewHolder(private val binding: OfferItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: OfferView) {
            binding.iconCircleStub.layoutResource = offer.iconRes
            binding.iconCircleStub.inflate()

            binding.textContent.text = offer.title
            binding.offerButton.setTextOrHide(offer.buttonText)

            binding.cardItem.setOnClickListener {
                itemView.context.openUrl(offer.link)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(offers[position])
    }

    override fun getItemCount(): Int = offers.size

    fun submitList(newOffers: List<OfferView>) {
        val diffResult = DiffUtil.calculateDiff(OffersDiffCallback(offers, newOffers))
        offers = newOffers
        diffResult.dispatchUpdatesTo(this)
    }
}

class OffersDiffCallback(
    private val oldList: List<OfferView>,
    private val newList: List<OfferView>
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
