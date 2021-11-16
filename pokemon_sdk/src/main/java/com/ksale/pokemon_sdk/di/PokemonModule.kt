package com.ksale.pokemon_sdk.di

import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.data.PokemonRepository
import com.ksale.pokemon_sdk.data.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ActivityComponent::class)
object PokemonModule {
    @Provides
    fun providePokemonRepository(pokemonService: PokemonService): PokemonRepository {
        return PokemonRepositoryImpl(pokemonService, Dispatchers.IO)
    }
}