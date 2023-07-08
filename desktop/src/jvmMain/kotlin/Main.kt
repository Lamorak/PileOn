import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cz.lamorak.pileon.common.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
