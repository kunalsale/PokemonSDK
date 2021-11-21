package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse
import kotlinx.coroutines.*
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Implementation class for [PokemonRepository]
 * It is marked internal so that the class is not visible to the client app.
 *
 * @param pokemonService: Retrofit interface to call pokemon API
 * @param defaultDispatcher Coroutine dispatcher in which the coroutine will be running,
 *        It is injected from outside
 */
internal class PokemonRepositoryImpl
@Inject constructor(
    private val pokemonService: PokemonService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : PokemonRepository {

    /**
     * It calls the [PokemonService] method to get the Pokemon detail
     * @param pokemonName Name of the pokemon
     */
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

    /**
     * It calls the [PokemonService] method to fetch the species of the Pokemon
     * @param pokemonName Name of the pokemon
     */
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