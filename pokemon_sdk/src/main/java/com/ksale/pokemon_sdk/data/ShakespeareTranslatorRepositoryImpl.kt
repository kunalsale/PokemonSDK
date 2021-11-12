package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.ShakespeareService
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShakespeareTranslatorRepositoryImpl(private val shakespeareService: ShakespeareService) :
    ShakespeareTranslatorRepository {
    override suspend fun fetchShakespeareTranslatedText(textToTranslate: String): Result<ShakespeareTranslationResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val response = shakespeareService.callTranslateDescriptionAPI(textToTranslate)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}