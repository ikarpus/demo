package com.iskar.demo.core.ui.ext

import android.view.View
import androidx.annotation.StringRes

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any): String =
    if (formatArgs.isEmpty()) resources.getString(resId) else resources.getString(
        resId,
        *formatArgs
    )