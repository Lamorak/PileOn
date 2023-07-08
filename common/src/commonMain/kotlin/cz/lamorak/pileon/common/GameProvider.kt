package cz.lamorak.pileon.common

import Game
import androidx.compose.runtime.compositionLocalOf

val GameProvider = compositionLocalOf { Game() }