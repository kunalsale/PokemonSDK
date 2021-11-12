package com.ksale.pokemon_sdk.usecase

import com.ksale.pokemon_sdk.api.models.Contents
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse
import com.ksale.pokemon_sdk.api.models.Success
import com.ksale.pokemon_sdk.data.ShakespeareTranslatorRepository
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
class ShakespeareUseCaseTest {

    @get:Rule
    val rules = CoroutineTestRule()
    private val testDispatcher = rules.testDispatcher

    @Test
    internal fun `should call repository fetchShakespeareTranslatedText method`() {
        testDispatcher.runBlockingTest {
            val repository = mock<ShakespeareTranslatorRepository>()
            whenever(repository.fetchShakespeareTranslatedText("abc"))
                .thenReturn(
                    com.ksale.pokemon_sdk.api.Result.Success(ShakespeareTranslationResponse(Contents("","",""), Success(1)))
                )
            val useCase = ShakespeareTranslatorUseCase(repository)
            useCase.translateToShakespeare("abc")
            verify(repository, times(1)).fetchShakespeareTranslatedText("abc")
        }
    }

    @Test
    internal fun `should return error if blank test is send`() {
        testDispatcher.runBlockingTest {
            val repository = mock<ShakespeareTranslatorRepository>()
            val useCase = ShakespeareTranslatorUseCase(repository)
            val response = useCase.translateToShakespeare("")
            assert(response is TranslatorState.ShakespeareError)
        }
    }
}