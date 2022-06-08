package com.iskar.demo.core.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

fun tickerFlow(initialDelay: Long = 0) = flow {
    delay(initialDelay)
    while (true) {
        emit(Unit)
        delay(1000)
    }
}