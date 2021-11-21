package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse

/**
 * Repository for Pokemon related task
 */
interface PokemonRepository {
    suspend fun fetchPokemon(pokemonName: String): Result<PokemonResponse>
    suspend fun fetchPokemonSpecies(pokemonName: String): Result<PokemonSpeciesResponse>
}