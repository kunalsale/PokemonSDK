package com.ksale.pokemonsdk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ksale.pokemon_sdk.api.PokemonService
import com.ksale.pokemon_sdk.data.PokemonRepositoryImpl
import com.ksale.pokemon_sdk.usecase.PokemonState
import com.ksale.pokemon_sdk.usecase.PokemonUseCase
import com.ksale.pokemonsdk.ui.theme.PokemonSDKTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemonRepository = PokemonRepositoryImpl(PokemonService.create())
        val pokemonUseCase = PokemonUseCase(pokemonRepository)
        CoroutineScope(Dispatchers.Main).launch {
            val response: PokemonState = pokemonUseCase.getPokemon("charizard")
            when (response) {
                is PokemonState.Pokemon -> {

                }
                is PokemonState.PokemonError -> {

                }
            }
        }
        setContent {
            PokemonSDKTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokemonSDKTheme {
        Greeting("Android")
    }
}