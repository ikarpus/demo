package com.iskar.demo.core.ui.ext

import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable

fun TextView.setCompoundDrawablesRes(
    @DrawableRes left: Int? = null,
    @DrawableRes top: Int? = null,
    @DrawableRes right: Int? = null,
    @DrawableRes bottom: Int? = null,
) {
    setCompoundDrawablesWithIntrinsicBounds(
        left?.let { getDrawable(context, left) },
        top?.let { getDrawable(context, top) },
        right?.let { getDrawable(context, right) },
        bottom?.let { getDrawable(context, bottom) })
}