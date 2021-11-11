package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.ShakespeareService
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse

class ShakespeareTranslatorRepositoryImpl(private val shakespeareService: ShakespeareService): ShakespeareTranslatorRepository {
    override suspend fun translateToShakespeare(textToTranslate: String): ShakespeareTranslationResponse {
        return shakespeareService.translateDescription(textToTranslate)
    }
}