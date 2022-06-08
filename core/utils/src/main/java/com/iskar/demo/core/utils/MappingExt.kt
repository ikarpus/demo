package com.iskar.demo.core.utils

inline fun <T, R : Any> Iterable<T>.mapCatching(transform: (T) -> R?): List<R> {
    val destination = mutableListOf<R>()
    forEach { element ->
        try {
            transform(element)?.let { destination.add(it) }
        } catch (e: Exception) {
            loge("mapCatching error: $e; \nCould not transform element: $element")
        }
    }
    return destination
}