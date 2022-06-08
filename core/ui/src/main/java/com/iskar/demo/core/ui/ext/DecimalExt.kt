package com.iskar.demo.core.ui.ext

import android.content.res.Resources

val Int.dp: Int get() = (Resources.getSystem().displayMetrics.density * this).toInt()
