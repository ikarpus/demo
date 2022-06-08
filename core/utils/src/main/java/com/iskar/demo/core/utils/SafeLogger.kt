package com.iskar.demo.core.utils

import android.util.Log

var LOGGING_ENABLED = BuildConfig.DEBUG

fun Any.loge(message: String?) {
    if (LOGGING_ENABLED) {
        Log.e("e".plus(TAG), message ?: "null")
    }
}

fun Any.loge(throwable: Throwable?) {
    if (LOGGING_ENABLED) {
        Log.e("e".plus(TAG), throwable?.message ?: "null")
    }
}

val <T : Any> T.TAG: String
    get() = if (this::class.java.simpleName.length > 16)
        "HA_".plus(this::class.java.simpleName.substring(0, 16))
    else
        "HA_".plus(this::class.java.simpleName)
