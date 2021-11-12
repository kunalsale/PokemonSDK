package com.ksale.pokemon_sdk.usecase

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.data.ShakespeareTranslatorRepository

class ShakespeareTranslatorUseCase(private val repository: ShakespeareTranslatorRepository) {
    suspend fun translateToShakespeare(textToTranslate: String): TranslatorState {
        return when(val response = repository.fetchShakespeareTranslatedText(textToTranslate)) {
            is Result.Success -> {
                TranslatorState.ShakespeareTranslated(response.data.contents.translated)
            }
            is Result.Error -> {
                TranslatorState.ShakespeareError(response.exception.message ?: "")
            }
        }
    }
}

sealed class TranslatorState {
    class ShakespeareTranslated(translated: String): TranslatorState()
    class ShakespeareError(errorMessage: String): TranslatorState()
}