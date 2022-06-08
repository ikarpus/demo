package com.iskar.demo.sports.ui.list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.iskar.demo.sports.ui.list.models.SportsListItem

class SportListItemDiffCallback : DiffUtil.ItemCallback<SportsListItem>() {
    override fun areItemsTheSame(oldItem: SportsListItem, newItem: SportsListItem): Boolean {
        return if (oldItem::class == newItem::class) {
            oldItem.id == newItem.id
        } else {
            false
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: SportsListItem, newItem: SportsListItem): Boolean {
        return oldItem == newItem
    }

}