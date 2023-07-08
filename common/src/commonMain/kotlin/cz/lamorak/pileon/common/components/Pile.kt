package cz.lamorak.pileon.common.components

import Draggable
import DraggedCards
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import cz.lamorak.pileon.common.GameProvider

@Composable
fun Pile(pileIndex: Int) {
    val dragState = DraggedCards.current
    val gameState = GameProvider.current

    val cards = gameState.state[pileIndex]

    val dragPosition = dragState.dragPosition
    val dragOffset = dragState.dragOffset

    val isTargetPile = dragState.isDragging && dragState.targetPile == pileIndex

    Box(
        Modifier
            .background(if (isTargetPile) Color(0x4400aaff) else Color(0x33ffffff))
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(1f)
            .padding(4.dp)
            .onGloballyPositioned {
                if (it.boundsInWindow().contains(dragPosition + dragOffset)) {
                    dragState.targetPile = pileIndex
                }
            }
    ) {
        cards.mapIndexed { index, card ->
            Box(
                Modifier.matchParentSize(),
                contentAlignment = { size, space, _ ->
                    val ofsetX = (space.width - size.width) / 3
                    dragState.pileOffset = ofsetX
                    IntOffset(ofsetX * index, 0)
                }
            ) {
                Draggable(
                    Modifier.alpha(if (dragState.draggedCards.contains(card)) 0f else 1f),
                    cards = {
                        gameState.getMovedCards(pileIndex, gameState.state[pileIndex].size - index)
                    },
                    onDragEnd = {
                        gameState.move(pileIndex, dragState.targetPile, gameState.state[pileIndex].size - index)
                    }
                ) {
                    Card(card, index)
                }
            }
        }
    }
}