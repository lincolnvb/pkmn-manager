package com.looqbox.pkmnmanager.domain.strategy

import com.looqbox.pkmnmanager.src.utils.SortUtils
import org.springframework.stereotype.Service

@Service
class AlphabeticalSortStrategy(private val sortUtils: SortUtils) : SortingStrategy{

    override fun supports(type: String?): Boolean = type == "alphabetical"

    override fun sort(pokemons: List<String>): List<String> {
        return sortUtils.mergeSort(pokemons)
    }
}