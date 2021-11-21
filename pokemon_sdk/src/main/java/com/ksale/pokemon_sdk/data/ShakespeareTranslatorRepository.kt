package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse

/**
 * Repository class for Shakespeare translation task
 */
interface ShakespeareTranslatorRepository {
    suspend fun fetchShakespeareTranslatedText(textToTranslate: String): Result<ShakespeareTranslationResponse>
}