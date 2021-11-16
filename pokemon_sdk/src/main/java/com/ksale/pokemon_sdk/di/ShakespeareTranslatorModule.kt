package com.ksale.pokemon_sdk.di

import com.ksale.pokemon_sdk.api.ShakespeareService
import com.ksale.pokemon_sdk.data.ShakespeareTranslatorRepository
import com.ksale.pokemon_sdk.data.ShakespeareTranslatorRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityComponent::class)
object ShakespeareTranslatorModule {
    @Provides
    fun provideShakespeareTranslatorRepository(
        service: ShakespeareService
    ): ShakespeareTranslatorRepository {
        return ShakespeareTranslatorRepositoryImpl(service, Dispatchers.IO)
    }
}