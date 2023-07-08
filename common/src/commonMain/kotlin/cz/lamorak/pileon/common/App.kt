package cz.lamorak.pileon.common

import cz.lamorak.pileon.common.components.Card
import DragInfo
import DraggedCards
import Game
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.IntOffset
import cz.lamorak.pileon.common.components.Table

@Composable
fun App() {
    val game = remember { Game() }
    val dragState = remember { DragInfo() }

    MaterialTheme {
        CompositionLocalProvider(
            DraggedCards provides dragState,
            GameProvider provides game,
        ) {
            Table()
            if (dragState.isDragging) {
                Box(modifier = Modifier
                    .size(dragState.cardSize)
                    .graphicsLayer {
                        val offset = (dragState.dragPosition + dragState.dragOffset)
                        translationX = offset.x
                        translationY = offset.y
                    }
                ) {
                    dragState.draggedCards.mapIndexed { index, card ->
                        Box(
                            contentAlignment = { _, _, _ -> IntOffset(dragState.pileOffset * index, 0) }
                        ) {
                            Card(card, index + 4)
                        }
                    }
                }
            }
        }
    }
}
