package com.ksale.pokemon_sdk.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

/**
 * Screen to show the info of Pokemon
 * 1. Pokemon name
 * 2. Sprite image of it
 * 3. Shakespearean translation of the description
 *
 * @param pokemonName Name of the pokemon
 * @param pokemonSpriteUrl URL for the sprite
 * @param shakeSpeareTranslation Shakespearean translated string
 */
@ExperimentalCoilApi
@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    pokemonSpriteUrl: String,
    shakeSpeareTranslation: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(top = 40.dp, start = 16.dp, end = 16.dp),
    ) {
        Text(
            text = pokemonName,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Image (
            painter = rememberImagePainter(pokemonSpriteUrl),
            contentDescription = "",
            modifier = Modifier.size(100.dp).padding(top = 16.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = shakeSpeareTranslation,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp),
            fontStyle = FontStyle.Italic
        )
    }
}