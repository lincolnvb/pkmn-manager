package com.looqbox.pkmnmanager.infrastructure.cache

import org.springframework.stereotype.Component

@Component
class PokeCache<T> {

    private val cache: MutableMap<String, T> = mutableMapOf()

    fun get(key: String): T? = cache[key]

    fun put(key: String, value: T) {
        cache[key] = value
    }

    fun invalidate(key: String) {
        cache.remove(key)
    }

    fun invalidateAll() {
        cache.clear()
    }
}


