package com.looqbox.pkmnmanager.domain.service


import com.looqbox.pkmnmanager.domain.model.PokemonHighlight
import com.looqbox.pkmnmanager.domain.strategy.SortingStrategy
import com.looqbox.pkmnmanager.infrastructure.cache.PokeCache
import com.looqbox.pkmnmanager.infrastructure.client.PokeApiClient
import org.springframework.stereotype.Service

@Service
class PokemonService(
    private val pokeApiClient: PokeApiClient,
    private val sortingStrategies: List<SortingStrategy>,
    private val cache: PokeCache<List<String>>
) {
    fun searchPokemons(query: String?, sortType: String): List<String> {
        val cachedResult = cache.get(query ?: "all")
        if (cachedResult != null) return sortPokemons(cachedResult, sortType)

        val pokemons = pokeApiClient.searchPokemons(query)
        cache.put(query ?: "all", pokemons)

        return sortPokemons(pokemons, sortType)
    }

    fun searchPokemonsWithHighlight(query: String?, sortType: String): List<PokemonHighlight> {
        val pokemons = searchPokemons(query, sortType)

        return pokemons.map { pokemonName ->
            val highlightedName = query?.let {
                pokemonName.replace(query, "<pre>$query</pre>", ignoreCase = true)
            } ?: pokemonName

            PokemonHighlight(name = pokemonName, highlight = highlightedName)
        }
    }

    private fun sortPokemons(pokemons: List<String>, sortType: String): List<String> {
        val strategy = sortingStrategies.find { it.supports(sortType) }
            ?: throw IllegalArgumentException("Unsupported sort type: $sortType")

        return strategy.sort(pokemons)
    }
}