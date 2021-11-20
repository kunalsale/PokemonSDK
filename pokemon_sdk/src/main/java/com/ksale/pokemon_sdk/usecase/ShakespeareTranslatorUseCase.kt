package com.ksale.pokemon_sdk.usecase

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.data.ShakespeareTranslatorRepository
import javax.inject.Inject

class ShakespeareTranslatorUseCase @Inject constructor(private val repository: ShakespeareTranslatorRepository) {
    suspend fun translateToShakespeare(textToTranslate: String): TranslatorState {
        if (textToTranslate.isBlank()) {
            return TranslatorState.ShakespeareError(false, 0, "Text is empty")
        }
        val formattedString = textToTranslate.replace("[\\t\\n\\r]+".toRegex(), " ");
        return when (val response = repository.fetchShakespeareTranslatedText(formattedString)) {
            is Result.Success -> {
                TranslatorState.ShakespeareTranslated(response.data.contents.translated)
            }
            is Result.Error -> {
                return when (response.errorCode) {
                    429 -> {
                        TranslatorState.ShakespeareError(
                            false,
                            429,
                            "Please retry again after sometime"
                        )
                    }
                    else -> {
                        TranslatorState.ShakespeareError(
                            true,
                            response.errorCode,
                            response.errorMessage ?: "Please retry again after sometime"
                        )
                    }
                }
            }
        }
    }
}

sealed class TranslatorState {
    class ShakespeareTranslated(val translated: String) : TranslatorState()
    class ShakespeareError(
        val shouldRetry: Boolean,
        val errorCode: Int,
        val errorMessage: String
    ) : TranslatorState()
}