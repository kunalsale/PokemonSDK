package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.ShakespeareService
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

internal class ShakespeareTranslatorRepositoryImpl
@Inject constructor(
    private val shakespeareService: ShakespeareService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ShakespeareTranslatorRepository {
    override suspend fun fetchShakespeareTranslatedText(textToTranslate: String): Result<ShakespeareTranslationResponse> {
        return withContext(dispatcher) {
            return@withContext try {
                val response = shakespeareService.callTranslateDescriptionAPI(textToTranslate)
                Result.Success(response)
            } catch (httpException: HttpException) {
                Result.Error(httpException.code(), httpException.message())
            } catch (e: Exception) {
                Result.Error(0, e.message)
            }
        }
    }
}
