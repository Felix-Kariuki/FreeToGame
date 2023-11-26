package com.flexcode.freetogame

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.flexcode.freetogame.presentation.views.GamesView
import com.flexcode.freetogame.ui.FreeToGameTheme

@Composable
fun App(
    darkTheme: Boolean, dynamicColor: Boolean
) {
    FreeToGameTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
        Navigator(GamesView())
    }
}