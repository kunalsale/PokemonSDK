package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.ShakespeareService
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Implementation class for [ShakespeareTranslatorRepository]
 * It is marked internal so that the class is not visible to the client app.
 *
 * @param shakespeareService: Retrofit interface to call Shakespeare translator API
 * @param defaultDispatcher Coroutine dispatcher in which the coroutine will be running,
 *        It is injected from outside
 */
internal class ShakespeareTranslatorRepositoryImpl
@Inject constructor(
    private val shakespeareService: ShakespeareService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ShakespeareTranslatorRepository {

    /**
     * It calls the [ShakespeareService] method to translate the given string
     * @param textToTranslate It takes the string as a param
     */
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
