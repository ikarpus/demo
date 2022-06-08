package com.iskar.demo.sports.ui.list.models

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.iskar.demo.feature.sports.ui.databinding.SportsItemSportBinding
import com.iskar.demo.sports.ui.list.SportEventItemDecoration

data class SportItem(
    override val id: String,
    val events: List<EventItem>
) : SportsListItem {

    companion object {

        fun delegate(
            onItemClickListener: (String) -> Unit
        ): AdapterDelegate<List<SportsListItem>> =
            adapterDelegateViewBinding<SportItem, SportsListItem, SportsItemSportBinding>(
                { layoutInflater, root ->
                    SportsItemSportBinding.inflate(layoutInflater, root, false)
                }
            ) {

                val adapter = AsyncListDifferDelegationAdapter(
                    EventItem.diffCallback(),
                    EventItem.delegate(onItemClickListener)
                )

                with(binding.rvContent) {
                    addItemDecoration(SportEventItemDecoration())
                    this.adapter = adapter
                    itemAnimator = null
                }

                bind {
                    adapter.setItems(item.events)
                }
            }
    }
}