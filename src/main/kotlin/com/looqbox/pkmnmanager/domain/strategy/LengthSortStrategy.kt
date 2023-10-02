package com.looqbox.pkmnmanager.domain.strategy

import com.looqbox.pkmnmanager.src.utils.SortUtils
import org.springframework.stereotype.Service

@Service
class LengthSortStrategy(private val sortUtils: SortUtils)  : SortingStrategy{

    override fun supports(type: String?): Boolean = type == "length"

    override fun sort(pokemons: List<String>): List<String> {
        return sortUtils.sortByLength(pokemons)
    }
}