package com.ksale.pokemon_sdk.api

import com.ksale.pokemon_sdk.api.models.PokemonResponse
import com.ksale.pokemon_sdk.api.models.PokemonSpeciesResponse
import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface PokemonService {

    @GET("pokemon/{pokemonName}")
    suspend fun callPokemonAPI(@Path("pokemonName") pokemonName: String): PokemonResponse

    @GET("pokemon-species/{pokemonName}")
    suspend fun callPokemonSpeciesAPI(@Path("pokemonName") pokemonName: String): PokemonSpeciesResponse

    companion object {
        fun create(): PokemonService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(PokemonService::class.java)
        }
    }
}