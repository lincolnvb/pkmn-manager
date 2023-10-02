package com.looqbox.pkmnmanager.domain.strategy

interface SortingStrategy {
    fun supports(type: String?): Boolean

    fun sort(pokemons: List<String>): List<String>
}