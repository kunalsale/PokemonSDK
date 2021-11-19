package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.ShakespeareService
import com.ksale.pokemon_sdk.rules.CoroutineTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class ShakespeareTranslatorRepositoryTest {

    @get:Rule
    val rules = CoroutineTestRule()
    private val testDispatcher = rules.testDispatcher

    @Test
    internal fun `should call the service method for the translator`() {
        testDispatcher.runBlockingTest {
            val translatorService = mock<ShakespeareService>()
            val repository = ShakespeareTranslatorRepositoryImpl(translatorService, testDispatcher)
            repository.fetchShakespeareTranslatedText("abc")
            verify(translatorService, times(1)).callTranslateDescriptionAPI("abc")
        }
    }
}