package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.rules.CoroutineTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class PokemonRepositoryTest {

    @get:Rule
    val rules = CoroutineTestRule()

    @Test
    internal fun `should call service pokemon method`() {
        rules.testDispatcher.runBlockingTest {
            val pokemonService = mock<PokemonService>()
            val pokemonRepository = PokemonRepositoryImpl(pokemonService, rules.testDispatcher)
            pokemonRepository.fetchPokemon("abc")
            verify(pokemonService, times(1)).callPokemonAPI("abc")
        }
    }

    @Test
    internal fun `should call service pokemon species method`() {
        rules.testDispatcher.runBlockingTest {
            val pokemonService = mock<PokemonService>()
            val pokemonRepository = PokemonRepositoryImpl(pokemonService, rules.testDispatcher)
            pokemonRepository.fetchPokemonSpecies("abc")
            verify(pokemonService, times(1)).callPokemonSpeciesAPI("abc")
        }
    }
}