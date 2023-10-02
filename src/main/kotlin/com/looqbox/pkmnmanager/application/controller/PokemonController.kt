package com.looqbox.pkmnmanager.application.controller

import com.looqbox.pkmnmanager.application.response.PokemonHighlightResponse
import com.looqbox.pkmnmanager.application.response.PokemonResponse
import com.looqbox.pkmnmanager.domain.service.PokemonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pokemons")
class PokemonController(private val pokemonService: PokemonService) {

    @GetMapping
    fun getPokemons(
        @RequestParam(name = "query", required = false) query: String?,
        @RequestParam(name = "sort", defaultValue = "alphabetical") sortType: String
    ): PokemonResponse {
        val pokemons = pokemonService.searchPokemons(query, sortType)
        return PokemonResponse(pokemons)
    }

    @GetMapping("/highlight")
    fun getPokemonsWithHighlight(
        @RequestParam(name = "query", required = false) query: String?,
        @RequestParam(name = "sort", defaultValue = "alphabetical") sortType: String
    ): PokemonHighlightResponse {
        val highlightedPokemons = pokemonService.searchPokemonsWithHighlight(query, sortType)
        return PokemonHighlightResponse(highlightedPokemons)
    }
}