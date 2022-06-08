package com.iskar.demo.sports.ui

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.iskar.demo.core.ui.base.BaseFragment
import com.iskar.demo.core.ui.ext.subscribe
import com.iskar.demo.feature.sports.ui.R
import com.iskar.demo.feature.sports.ui.databinding.SportsFragmentSportsBinding
import com.iskar.demo.sports.ui.list.SportListItemDiffCallback
import com.iskar.demo.sports.ui.list.models.HeaderItem
import com.iskar.demo.sports.ui.list.models.SportItem

class SportsFragment : BaseFragment<SportsViewModel, SportsFragmentSportsBinding>(
    R.layout.sports_fragment_sports,
    SportsFragmentSportsBinding::bind
) {

    private val adapter by lazy {
        AsyncListDifferDelegationAdapter(
            SportListItemDiffCallback(),
            HeaderItem.delegate(viewModel::swapExpandedStatus),
            SportItem.delegate(viewModel::swapFavoriteStatus),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            rvContent.itemAnimator = null
            rvContent.adapter = adapter
            srlRefresh.setOnRefreshListener {
                viewModel.fetchSports()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()

        subscribe(viewModel.sports) {
            adapter.setItems(it)
        }

        subscribe(viewModel.isLoading) {
            binding.srlRefresh.isRefreshing = it
        }
    }
}