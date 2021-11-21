package com.ksale.pokemon_sdk.di

import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.api.ShakespeareService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesPokemonService(): PokemonService {
        return PokemonService.create()
    }

    @Singleton
    @Provides
    fun providesTranslatorService(): ShakespeareService {
        return ShakespeareService.create()
    }
}