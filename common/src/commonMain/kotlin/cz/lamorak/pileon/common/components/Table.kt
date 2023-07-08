package cz.lamorak.pileon.common.components

import Game
//import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import cz.lamorak.pileon.common.GameProvider

@Composable
fun Table() {
    var columns by remember { mutableStateOf(5) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF003313))
            .padding(16.dp)
            .onSizeChanged {
                columns = when (it.width / it.height.toDouble()) {
                    in 0.0..0.6 -> 3
                    in 0.6..1.33 -> 4
                    else -> 5
                }
            }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Center),
        ) {
            items(15) {
                Pile(it)
            }
        }
    }
}

//@Preview
//@Composable
//fun TablePreview() {
//    CompositionLocalProvider(
//        GameProvider provides Game()
//    ) {
//        Table()
//    }
//}