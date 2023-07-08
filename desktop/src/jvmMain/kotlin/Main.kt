import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cz.lamorak.pileon.common.App


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PileOn Solitaire",
        state = rememberWindowState(),
    ) {
        App()
    }
}
