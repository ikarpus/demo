package com.iskar.demo.core.utils

interface SuspendableMapper<In, Out> {
    suspend fun map(input: In): Out
}