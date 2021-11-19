package com.ksale.pokemon_sdk.usecase

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.data.ShakespeareTranslatorRepository
import javax.inject.Inject

class ShakespeareTranslatorUseCase @Inject constructor(private val repository: ShakespeareTranslatorRepository) {
    suspend fun translateToShakespeare(textToTranslate: String): TranslatorState {
        if (textToTranslate.isBlank()) {
            return TranslatorState.ShakespeareError("Text is empty")
        }
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