import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize

val DraggedCards = compositionLocalOf { DragInfo() }

class DragInfo {
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)

    var pileOffset by mutableStateOf(0)
    var cardSize by mutableStateOf(DpSize.Zero)

    var draggedCards by mutableStateOf(emptyList<Card>())
    var targetPile by mutableStateOf(-1)

    val isDragging
        get() = draggedCards.isNotEmpty()

    fun reset() {
        dragOffset = Offset.Zero
        draggedCards = emptyList()
    }
}

@Composable
fun Draggable(
    modifier: Modifier,
    cards: () -> List<Card>,
    onDragEnd: () -> Unit,
    content: @Composable (() -> Unit)
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    var currentSize by remember { mutableStateOf(DpSize.Zero) }
    val dragState = DraggedCards.current
    val denstity = LocalDensity.current

    Box(
        modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
            }
            .onSizeChanged {
                denstity.run {
                    currentSize = DpSize(it.width.toDp(), it.height.toDp())
                }
            }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        dragState.apply {
                            dragPosition = currentPosition
                            cardSize = currentSize
                            draggedCards = cards()
                        }
                    },
                    onDragEnd = {
                        dragState.reset()
                        onDragEnd()
                    },
                    onDragCancel = { dragState.reset() },
                    onDrag = { change, dragAmount ->
                        change.consumeAllChanges()
                        dragState.dragOffset += dragAmount
                    }
                )
            }
    ) { content() }
}
