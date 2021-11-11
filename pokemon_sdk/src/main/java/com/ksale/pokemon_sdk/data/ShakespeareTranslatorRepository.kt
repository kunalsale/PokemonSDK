package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse

interface ShakespeareTranslatorRepository {
    suspend fun translateToShakespeare(textToTranslate: String): ShakespeareTranslationResponse
}