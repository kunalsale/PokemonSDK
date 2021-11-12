package com.ksale.pokemon_sdk.api

import com.ksale.pokemon_sdk.api.models.ShakespeareTranslationResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ShakespeareService {
    @GET("/translate/shakespeare.json")
    suspend fun callTranslateDescriptionAPI(@Query("text") text: String): ShakespeareTranslationResponse

    companion object {
        fun create(): ShakespeareService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.funtranslations.com/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ShakespeareService::class.java)
        }
    }

}