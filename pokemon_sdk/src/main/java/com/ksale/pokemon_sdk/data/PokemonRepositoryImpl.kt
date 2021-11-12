package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PokemonRepositoryImpl(private val pokemonService: PokemonService) : PokemonRepository {
    override suspend fun fetchPokemon(pokemonName: String): Result<PokemonResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val response = pokemonService.callPokemonAPI(pokemonName)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun fetchPokemonSpecies(pokemonName: String): Result<PokemonSpeciesResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val response = pokemonService.callPokemonSpeciesAPI(pokemonName)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}