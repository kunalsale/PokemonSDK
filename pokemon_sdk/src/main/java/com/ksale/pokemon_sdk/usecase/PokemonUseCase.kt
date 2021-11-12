package com.ksale.pokemon_sdk.usecase

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse
import com.ksale.pokemon_sdk.data.PokemonRepository

class PokemonUseCase(private val pokemonRepository: PokemonRepository) {
    suspend fun getPokemon(pokemonName: String): PokemonState {
        if (pokemonName.isBlank()) {
            return PokemonState.PokemonError("Pokemon name cannot be blank")
        }
        val lowerCasePokemon = pokemonName.lowercase()
        return when (val response = pokemonRepository.fetchPokemon(lowerCasePokemon)) {
            is Result.Success -> {
                PokemonState.Pokemon(response.data)
            }
            is Result.Error -> {
                PokemonState.PokemonError(response.exception.message ?: "Something went wrong")
            }
        }
    }

    suspend fun getPokemonSpecies(pokemonName: String): PokemonState {
        if (pokemonName.isNotBlank()) {
            return PokemonState.PokemonError("Pokemon name cannot be blank")
        }
        val lowerCasePokemon = pokemonName.lowercase()
        return when (val response = pokemonRepository.fetchPokemonSpecies(lowerCasePokemon)) {
            is Result.Success -> {
                PokemonState.PokemonSpecies(response.data)
            }
            is Result.Error -> {
                PokemonState.PokemonError(response.exception.message ?: "Something went wrong")
            }
        }
    }
}

sealed class PokemonState {
    class Pokemon(response: PokemonResponse): PokemonState()
    class PokemonSpecies(response: PokemonSpeciesResponse): PokemonState()
    class PokemonError(errorMessage: String): PokemonState()
}