package com.iskar.demo.sports.ui.list.models

import androidx.annotation.DrawableRes
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.iskar.demo.core.ui.ext.setCompoundDrawablesRes
import com.iskar.demo.feature.sports.ui.databinding.SportsItemHeaderBinding

data class HeaderItem(
    override val id: String,
    val title: String,
    @DrawableRes val icon: Int?,
    val isExpanded: Boolean = false
) : SportsListItem {

    companion object {

        fun delegate(
            onItemClickListener: (String) -> Unit
        ): AdapterDelegate<List<SportsListItem>> =
            adapterDelegateViewBinding<HeaderItem, SportsListItem, SportsItemHeaderBinding>(
                { layoutInflater, root ->
                    SportsItemHeaderBinding.inflate(layoutInflater, root, false)
                }
            ) {
                bind {
                    with(binding) {
                        root.setOnClickListener {
                            onItemClickListener.invoke(item.id)
                        }
                        tvTitle.text = item.title
                        tvTitle.setCompoundDrawablesRes(left = item.icon)

                        ivExpanded.isSelected = item.isExpanded

                    }
                }
            }
    }
}