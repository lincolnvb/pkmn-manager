package com.looqbox.pkmnmanager.infrastructure.client

import com.looqbox.pkmnmanager.infrastructure.client.response.PokeApiResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PokeApiClient(
    private val restTemplate: RestTemplate
) {

    private val baseUrl = "https://pokeapi.co/api/v2"

    fun searchPokemons(query: String?): List<String> {
        val response = restTemplate.getForObject("$baseUrl/pokemon/?limit=1292", PokeApiResponse::class.java)
        return response?.results?.map { it.name }?.filter { it.contains(query ?: "", ignoreCase = true) } ?: emptyList()
    }
}