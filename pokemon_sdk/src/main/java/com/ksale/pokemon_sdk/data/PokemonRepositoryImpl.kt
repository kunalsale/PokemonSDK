package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse

class PokemonRepositoryImpl(private val pokemonService: PokemonService): PokemonRepository {
    override suspend fun fetchPokemon(pokemonName: String): PokemonResponse {
        return pokemonService.pokemon(pokemonName)
    }

    override suspend fun fetchPokemonSpecies(pokemonName: String): PokemonSpeciesResponse {
        return pokemonService.pokemonSpecies(pokemonName)
    }
}