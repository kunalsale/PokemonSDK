package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject

internal class PokemonRepositoryImpl
@Inject constructor(
    private val pokemonService: PokemonService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : PokemonRepository {
    override suspend fun fetchPokemon(pokemonName: String): Result<PokemonResponse> {
        return withContext(defaultDispatcher) {
            return@withContext try {
                val response = pokemonService.callPokemonAPI(pokemonName)
                Result.Success(response)
            } catch (httpException: HttpException) {
                Result.Error(httpException.code(), httpException.message())
            } catch (e: Exception) {
                Result.Error(0, e.message)
            }
        }
    }

    override suspend fun fetchPokemonSpecies(pokemonName: String): Result<PokemonSpeciesResponse> {
        return withContext(defaultDispatcher) {
            return@withContext try {
                val response = pokemonService.callPokemonSpeciesAPI(pokemonName)
                Result.Success(response)
            } catch (httpException: HttpException) {
                Result.Error(httpException.code(), httpException.message())
            } catch (e: Exception) {
                Result.Error(0, e.message)
            }
        }
    }
}