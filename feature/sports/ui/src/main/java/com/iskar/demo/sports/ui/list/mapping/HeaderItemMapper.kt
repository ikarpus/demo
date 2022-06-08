package com.iskar.demo.sports.ui.list.mapping

import com.iskar.demo.feature.sports.ui.R
import com.iskar.demo.sports.domain.model.Sport
import com.iskar.demo.sports.ui.list.models.HeaderItem

private val icons = mapOf(
    "FOOT" to R.drawable.sports_ic_football,
    "BASK" to R.drawable.sports_ic_basketball,
    "TENN" to R.drawable.sports_ic_tennis,
    "TABL" to R.drawable.sports_ic_table_tennis,
    "VOLL" to R.drawable.sports_ic_volleyball,
    "ESPS" to R.drawable.sports_ic_esport,
    "ICEH" to R.drawable.sports_ic_hockey,
    "BCHV" to R.drawable.sports_ic_volleyball,
    "BADM" to R.drawable.sports_ic_tennis,
)

fun Sport.toHeaderItem(isExpanded: Boolean): HeaderItem = HeaderItem(
    id = id,
    title = name,
    icon = icons[id],
    isExpanded = isExpanded
)