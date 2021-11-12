package com.ksale.pokemon_sdk.data

import com.ksale.pokemon_sdk.api.Result
import com.ksale.pokemon_sdk.api.ShakespeareService
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class ShakespeareTranslatorRepositoryImpl
    @Inject constructor(private val shakespeareService: ShakespeareService,
                        private val dispatcher: CoroutineDispatcher = Dispatchers.Default) : ShakespeareTranslatorRepository {
    override suspend fun fetchShakespeareTranslatedText(textToTranslate: String): Result<ShakespeareTranslationResponse> {
        return withContext(dispatcher) {
            return@withContext try {
                val response = shakespeareService.callTranslateDescriptionAPI(textToTranslate)
                Result.Success(response)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
object TranslatorModule {
    @Provides
    fun provideShakespeareTranslatorRepository(
        service: ShakespeareService
    ): ShakespeareTranslatorRepository {
        return ShakespeareTranslatorRepositoryImpl(service, Dispatchers.IO)
    }
}