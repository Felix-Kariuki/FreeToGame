package com.flexcode.freetogame

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Free to game",
    ) {
        App(darkTheme = isSystemInDarkTheme(), dynamicColor = true)
    }
}