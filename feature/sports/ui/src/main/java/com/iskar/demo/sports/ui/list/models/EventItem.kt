package com.iskar.demo.sports.ui.list.models

import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.iskar.demo.feature.sports.ui.databinding.SportsItemEventBinding

data class EventItem(
    override val id: String,
    val playerOne: String?,
    val playerTwo: String?,
    val startTime: Long?,
    val isFavorite: Boolean,
) : SportsListItem {

    companion object {

        fun delegate(
            onItemClickListener: (String) -> Unit
        ): AdapterDelegate<List<EventItem>> =
            adapterDelegateViewBinding({ layoutInflater, root ->
                SportsItemEventBinding.inflate(layoutInflater, root, false)
            }) {

                bind {
                    with(binding) {
                        ivFavorite.setOnClickListener {
                            onItemClickListener.invoke(item.id)
                        }
                        ivFavorite.isSelected = item.isFavorite

                        tvPlayerOne.text = item.playerOne
                        tvPlayerOne.isVisible = item.playerOne != null

                        tvPlayerTwo.text = item.playerTwo
                        tvPlayerTwo.isVisible = item.playerTwo != null

                        tvTimeLeft.isVisible = item.startTime != null
                        item.startTime?.also {
                            tvTimeLeft.setTime(it)
                        }
                    }
                }
            }

        fun diffCallback() = object : DiffUtil.ItemCallback<EventItem>() {
            override fun areItemsTheSame(
                oldItem: EventItem,
                newItem: EventItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: EventItem,
                newItem: EventItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}