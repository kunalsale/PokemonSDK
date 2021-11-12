package com.ksale.pokemon_sdk.usecase

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse
import com.ksale.pokemon_sdk.api.models.Sprites
import com.ksale.pokemon_sdk.data.PokemonRepository
import com.ksale.pokemon_sdk.rules.CoroutineTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class PokemonUseCaseTest {

    @get:Rule
    val rules = CoroutineTestRule()
    private val testDispatcher = rules.testDispatcher

    private val pokemonRepository = mock<PokemonRepository>()
    private val pokemonUseCase = PokemonUseCase(pokemonRepository)

    @Test
    internal fun `should call repository fetchPokemon method`() {
        testDispatcher.runBlockingTest {
            whenever(pokemonRepository.fetchPokemon("abc"))
                .thenReturn(
                    Result.Success(PokemonResponse(Sprites("","","","","","","","")))
                )
            pokemonUseCase.getPokemon("abc")
            verify(pokemonRepository, times(1)).fetchPokemon("abc")
        }
    }

    @Test
    internal fun `should call repository fetchPokemonSpecies method`() {
        testDispatcher.runBlockingTest {
            whenever(pokemonRepository.fetchPokemonSpecies("abc"))
                .thenReturn(
                    Result.Success(PokemonSpeciesResponse(listOf()))
                )
            pokemonUseCase.getPokemonSpecies("abc")
            verify(pokemonRepository, times(1)).fetchPokemonSpecies("abc")
        }
    }

    @Test
    internal fun `should return error if pokemon name is blank is sent in the pokemon api`() {
        testDispatcher.runBlockingTest {
            val result = pokemonUseCase.getPokemon("")
            assert(result is PokemonState.PokemonError)
        }
    }
    @Test
    internal fun `should return error if pokemon name is blank is sent in the pokemon species api`() {
        testDispatcher.runBlockingTest {
            val result = pokemonUseCase.getPokemonSpecies("")
            assert(result is PokemonState.PokemonError)
        }
    }
}